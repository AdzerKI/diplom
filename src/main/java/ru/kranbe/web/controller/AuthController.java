package ru.kranbe.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kranbe.service.user.AuthenticationService;
import ru.kranbe.service.user.RegistrationService;
import ru.kranbe.service.user.RestoreService;
import ru.kranbe.service.user.VerificationService;
import ru.kranbe.web.dto.user.auth.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/rest/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Controller", description = "Auth API")
@Validated
public class AuthController {
    private final AuthenticationService authenticationService;
    private final RegistrationService registrationService;
    private final VerificationService verificationService;
    private final RestoreService restoreService;

    // TODO удаление после даты expired
    @PostMapping("/registration-start")
    public RegistrationResponse registration(@Valid @RequestBody final RegistrationRequest request)
            throws MessagingException, UnsupportedEncodingException {
        return registrationService.registration(request);
    }

    @PostMapping("/registration-finish")
    public VerificationRegistrationResponse verificationRegistration(@Valid @RequestBody final VerificationRegistrationRequest request)
            throws MessagingException, UnsupportedEncodingException {
        return verificationService.verificationRegistration(request);
    }


    @PostMapping("/restore-start")
    public RestoreResponse restore(@Valid @RequestBody final RestoreRequest request)
            throws MessagingException, UnsupportedEncodingException {
        return restoreService.restore(request);
    }

    @PostMapping("/restore-finish")
    public VerificationRestoreResponse verificationRestore(@Valid @RequestBody final VerificationRestoreRequest request)
            throws MessagingException, UnsupportedEncodingException {
        return verificationService.verificationRestore(request);
    }

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody final JwtRequest request) {
        return authenticationService.login(request);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@Valid @RequestBody final JwtRefreshRequest request) {
        return authenticationService.refresh(request);
    }

}