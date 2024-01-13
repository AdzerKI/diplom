package ru.kranbe.service.user;

import ru.kranbe.web.dto.user.auth.JwtRefreshRequest;
import ru.kranbe.web.dto.user.auth.JwtRequest;
import ru.kranbe.web.dto.user.auth.JwtResponse;

public interface AuthenticationService {
    JwtResponse login(final JwtRequest request);

    JwtResponse refresh(final JwtRefreshRequest request);
}
