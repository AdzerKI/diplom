package ru.kranbe.service.user;

import jakarta.mail.MessagingException;
import ru.kranbe.web.dto.user.auth.RegistrationRequest;
import ru.kranbe.web.dto.user.auth.RegistrationResponse;

import java.io.UnsupportedEncodingException;

public interface RegistrationService {
    RegistrationResponse registration(final RegistrationRequest request)
            throws UnsupportedEncodingException, MessagingException;
}
