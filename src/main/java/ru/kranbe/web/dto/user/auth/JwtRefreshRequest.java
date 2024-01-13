package ru.kranbe.web.dto.user.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
@Schema(description = "Request for refresh token")
public class JwtRefreshRequest {
    @Schema(description = "Refresh Token", example = "eyJhbGciOiJIUzI1N.....")
    @NotBlank
    private String token;
}