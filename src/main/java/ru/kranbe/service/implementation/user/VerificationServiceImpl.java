package ru.kranbe.service.implementation.user;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kranbe.domain.Status;
import ru.kranbe.domain.user.User;
import ru.kranbe.domain.user.verification.VerificationEntity;
import ru.kranbe.domain.exception.ResourceNotFoundException;
import ru.kranbe.domain.user.verification.Type;
import ru.kranbe.repository.UserRepository;
import ru.kranbe.repository.VerificationRepository;
import ru.kranbe.service.EmailService;
import ru.kranbe.service.helper.ExpireDate;
import ru.kranbe.service.helper.RandomPassword;
import ru.kranbe.service.user.UserService;
import ru.kranbe.service.user.VerificationService;
import ru.kranbe.web.dto.user.auth.VerificationRegistrationRequest;
import ru.kranbe.web.dto.user.auth.VerificationRegistrationResponse;
import ru.kranbe.web.dto.user.auth.VerificationRestoreRequest;
import ru.kranbe.web.dto.user.auth.VerificationRestoreResponse;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {
    private final UserRepository userRepository;
    private final VerificationRepository verificationRepository;
    private final EmailService emailService;
    private final UserService userService;
    private final RandomPassword randomPassword;
    private final ExpireDate expireDate;

    @Override
    public VerificationRegistrationResponse verificationRegistration(final VerificationRegistrationRequest request)
            throws MessagingException, UnsupportedEncodingException {
        if(verificationRepository.findByToken(request.getToken()).isPresent()) {
            VerificationRegistrationResponse response = new VerificationRegistrationResponse();
            VerificationEntity verificationEntity = getByToken(request.getToken());
            delete(verificationEntity.getId()); // удаляем верификацию

            Map<String, Object> variables = userService.create(verificationEntity.getEmail()); // создаем пользователя

            response.setMessage("Пользователь зарегистрирован.");
            response.setUsername(String.valueOf(variables.get("username")));
            response.setPassword(String.valueOf(variables.get("password")));

            return response;
        } else {
            throw new ResourceNotFoundException("Код подтверждения не существует или устарел.");
        }
    }

    @Override
    public VerificationRestoreResponse verificationRestore(final VerificationRestoreRequest request)
            throws MessagingException, UnsupportedEncodingException {
        if(verificationRepository.findByToken(request.getToken()).isPresent()) {
            VerificationRestoreResponse response = new VerificationRestoreResponse();
            VerificationEntity verificationEntity = getByToken(request.getToken());
            delete(verificationEntity.getId()); // удаляем верификацию

            User user = userService.getByEmail(verificationEntity.getEmail()); // находим пользователя
            String[] password = randomPassword.setEncodedPassword(10); // Пароль [0] - plain, [1] - encoded
            user.setPassword(password[1]);
            userRepository.save(user);

            // Для письма пользователю поля
            Map<String, Object> variables = new HashMap<>();
            variables.put("username", user.getUsername());
            variables.put("password", password[0]);

            response.setMessage("Пароль пользователя обновлен");
            response.setUsername(String.valueOf(variables.get("username")));
            response.setPassword(String.valueOf(variables.get("password")));

            emailService.sendEmail(user.getEmail(), "Ваш пароль обновлен",
                    "html/mail/auth/restore_finish.html", variables);

            return response;
        } else {
            throw new ResourceNotFoundException("Код подтверждения не существует или устарел.");
        }
    }

    @Override
    public VerificationEntity create(String email, Type status){
        // Получаем уникальный ключ
        String token = UUID.randomUUID().toString();

        // Создаем запись в таблице подтверждений
        VerificationEntity verificationEntity = new VerificationEntity();
        verificationEntity.setEmail(email);
        verificationEntity.setToken(token);
        verificationEntity.setExpire(expireDate.calculateExpiryDate(24));
        verificationEntity.setType(status);
        verificationEntity.setStatus(Status.ACTIVE);
        verificationRepository.save(verificationEntity);

        return verificationEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public VerificationEntity getByToken(final String token) {
        return verificationRepository.findByToken(token)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Verification id not found."));
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        verificationRepository.deleteById(id);
    }
}
