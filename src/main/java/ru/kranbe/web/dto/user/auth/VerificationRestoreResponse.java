package ru.kranbe.web.dto.user.auth;

import lombok.Data;


@Data
public class VerificationRestoreResponse {
    private String message;
    private String username;
    private String password;
}
