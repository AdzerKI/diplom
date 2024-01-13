package ru.kranbe.web.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.kranbe.domain.Status;
import ru.kranbe.domain.user.verification.Type;

import java.util.Date;

@Data
@Schema(description = "Verification DTO", accessMode = Schema.AccessMode.READ_ONLY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerificationDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Schema(description = "Verification request email")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String email;

    @Schema(description = "Verification request token")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String token;

    @Schema(description = "Verification request expire")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date expire;

    @Schema(description = "Verification request type")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Type type;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Status status;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date created;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updated;
}
