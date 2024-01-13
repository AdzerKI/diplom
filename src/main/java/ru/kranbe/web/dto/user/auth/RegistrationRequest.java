package ru.kranbe.web.dto.user.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Schema(description = "Registration request")
public class RegistrationRequest {
    @Schema(description = "Agreement", example = "true")
    @NotNull
    @AssertTrue(message = "Необходимо согласие на обработку персональных данных")
    private Boolean agreement;

    @Schema(description = "Email", example = "johndoe@gmail.com")
    @NotBlank
    @Email
    private String email;
}
