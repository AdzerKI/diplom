package ru.kranbe.service.implementation.user;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kranbe.domain.exception.ResourceAlreadyExistException;
import ru.kranbe.domain.user.User;
import ru.kranbe.domain.user.verification.VerificationEntity;
import ru.kranbe.domain.user.verification.Type;
import ru.kranbe.repository.UserRepository;
import ru.kranbe.repository.VerificationRepository;
import ru.kranbe.service.EmailService;
import ru.kranbe.service.user.RestoreService;
import ru.kranbe.service.user.UserService;
import ru.kranbe.service.user.VerificationService;
import ru.kranbe.web.dto.user.auth.RestoreRequest;
import ru.kranbe.web.dto.user.auth.RestoreResponse;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestoreServiceImpl implements RestoreService {
    private final EmailService emailService;
    private final VerificationService verificationService;
    private final VerificationRepository verificationRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public RestoreResponse restore(final RestoreRequest request)
            throws UnsupportedEncodingException, MessagingException {
        RestoreResponse response = new RestoreResponse();

        if (verificationRepository.findByEmail(request.getUsername()).isPresent()) {
            throw new ResourceAlreadyExistException("Регистрация с данного Email уже запрошена, проверьте " +
                    "указанную почту (возможно письмо попало в спам)");
        }

        User user = userService.getByUsernameOrEmailOrPhone(request.getUsername());

        VerificationEntity verificationEntity = verificationService.create(user.getEmail(), Type.RESTORE);

        Map<String, Object> variables = Map.of(
                "verificationURL", "https://kranbe.ru/auth/confirm/restore/" + verificationEntity.getToken());

        emailService.sendEmail(user.getEmail(), "Запрос на восстановление пароля",
                "html/mail/auth/restore_start.html", variables);

        response.setMessage("Подтвердите адрес электронной почты, письмо направлено.");

        return response;
    }
}
