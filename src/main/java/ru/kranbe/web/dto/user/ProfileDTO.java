package ru.kranbe.web.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.kranbe.domain.Status;
import ru.kranbe.web.dto.validation.OnCreate;
import ru.kranbe.web.dto.validation.OnUpdate;

import java.util.Date;

@Data
@Schema(description = "Profile DTO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Profile lastName", example = "Иванов")
    @Length(max = 100,
            message = "lastName length must be smaller than 100 symbols.",
            groups = {OnCreate.class, OnUpdate.class})
    private String lastName;

    @Schema(description = "Profile firstName", example = "Иван")
    @Length(max = 100,
            message = "firstName length must be smaller than 100 symbols.",
            groups = {OnCreate.class, OnUpdate.class})
    private String firstName;

    @Schema(description = "Profile patronymic", example = "Иванович")
    @Length(max = 100,
            message = "Patronymic length must be smaller than 100 symbols.",
            groups = {OnCreate.class, OnUpdate.class})
    private String patronymic;

    @Schema(description = "Profile avatar", example = "user")
    @Length(max = 255,
            message = "Avatar length must be smaller than 255 symbols.",
            groups = {OnCreate.class, OnUpdate.class})
    private String avatar;

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
