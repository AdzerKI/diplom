package ru.kranbe.web.dto.user.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Restore request")
public class RestoreRequest {
    @Schema(description = "Username or Email or Phone", example = "johndoe@gmail.com")
    @NotNull
    private String username;
}
