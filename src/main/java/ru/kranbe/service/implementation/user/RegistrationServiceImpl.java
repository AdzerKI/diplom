package ru.kranbe.service.implementation.user;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import ru.kranbe.domain.exception.ResourceAlreadyExistException;
import ru.kranbe.domain.user.verification.Type;
import ru.kranbe.domain.user.verification.VerificationEntity;
import ru.kranbe.repository.UserRepository;
import ru.kranbe.repository.VerificationRepository;
import ru.kranbe.service.*;
import ru.kranbe.service.user.RegistrationService;
import ru.kranbe.service.user.VerificationService;
import ru.kranbe.web.dto.user.auth.RegistrationRequest;
import ru.kranbe.web.dto.user.auth.RegistrationResponse;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final EmailService emailService;
    private final VerificationRepository verificationRepository;
    private final VerificationService verificationService;
    private final UserRepository userRepository;

    @Override
    public RegistrationResponse registration(final RegistrationRequest request)
            throws MessagingException, UnsupportedEncodingException {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistException("Email уже используется.");
        }

        if (verificationRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistException("Регистрация с данного Email уже запрошена, проверьте " +
                    "указанную почту (возможно письмо попало в спам)");
        }

        RegistrationResponse response = new RegistrationResponse();

        VerificationEntity verificationEntity = verificationService.create(request.getEmail(), Type.ACTIVATION);

        Map<String, Object> variables = Map.of(
                "verificationURL", "https://kranbe.ru/auth/confirm/registration/" + verificationEntity.getToken());

        emailService.sendEmail(request.getEmail(), "Подтверждение адреса электронной почты",
                "html/mail/auth/registration_start.html", variables);

        response.setMessage("Подтвердите адрес электронной почты, письмо направлено.");

        return response;
    }
}
