package ru.kranbe.web.dto.user.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
@Schema(description = "Request for login")
public class JwtRequest {
    @Schema(description = "Username or Email or Phone", example = "johndoe@gmail.com")
    @NotBlank
    private String username;

    @Schema(description = "password", example = "12345")
    @NotBlank
    private String password;
}