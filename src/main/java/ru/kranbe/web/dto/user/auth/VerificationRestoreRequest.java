package ru.kranbe.web.dto.user.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Verification restore request")
public class VerificationRestoreRequest {
    @Schema(description = "Token", example = "3422b448-2460-4fd2-9183-8000de6f8343")
    @NotBlank
    private String token;
}
