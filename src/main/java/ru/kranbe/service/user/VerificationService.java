package ru.kranbe.service.user;


import jakarta.mail.MessagingException;
import ru.kranbe.domain.user.verification.Type;
import ru.kranbe.domain.user.verification.VerificationEntity;
import ru.kranbe.web.dto.user.auth.VerificationRegistrationRequest;
import ru.kranbe.web.dto.user.auth.VerificationRegistrationResponse;
import ru.kranbe.web.dto.user.auth.VerificationRestoreRequest;
import ru.kranbe.web.dto.user.auth.VerificationRestoreResponse;

import java.io.UnsupportedEncodingException;

public interface VerificationService {
    VerificationRegistrationResponse verificationRegistration(VerificationRegistrationRequest request)
            throws UnsupportedEncodingException, MessagingException;

    VerificationRestoreResponse verificationRestore(VerificationRestoreRequest request)
            throws UnsupportedEncodingException, MessagingException;

    VerificationEntity create(String email, Type status);

    VerificationEntity getByToken(String token);

    void delete(Long id);
}
