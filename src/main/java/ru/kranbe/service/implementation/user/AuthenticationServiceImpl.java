package ru.kranbe.service.implementation.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.kranbe.domain.user.User;
import ru.kranbe.service.user.AuthenticationService;
import ru.kranbe.service.user.UserService;
import ru.kranbe.web.dto.user.auth.JwtRefreshRequest;
import ru.kranbe.web.dto.user.auth.JwtRequest;
import ru.kranbe.web.dto.user.auth.JwtResponse;
import ru.kranbe.web.security.JwtTokenProvider;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    public JwtResponse login(final JwtRequest request) {
        JwtResponse response = new JwtResponse();

        User user = userService.getByUsernameOrEmailOrPhone(request.getUsername());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(), request.getPassword())
        );

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setAccessToken(jwtTokenProvider.createAccessToken(
                user.getId(), user.getUsername(), user.getRoles())
        );
        response.setRefreshToken(jwtTokenProvider.createRefreshToken(
                user.getId(), user.getUsername())
        );

        return response;
    }

    @Override
    public JwtResponse refresh(final JwtRefreshRequest request) {
        return jwtTokenProvider.refreshUserTokens(request);
    }

}
