package ru.kranbe.web.dto.user.auth;

import lombok.Data;


@Data
public class VerificationRegistrationResponse {
    private String message;
    private String username;
    private String password;
}
