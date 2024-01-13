package ru.kranbe.service.user;

import jakarta.mail.MessagingException;
import ru.kranbe.web.dto.user.auth.RestoreRequest;
import ru.kranbe.web.dto.user.auth.RestoreResponse;

import java.io.UnsupportedEncodingException;

public interface RestoreService {
    RestoreResponse restore(final RestoreRequest request)
            throws UnsupportedEncodingException, MessagingException;
}
