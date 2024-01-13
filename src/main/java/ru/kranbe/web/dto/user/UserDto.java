package ru.kranbe.web.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.kranbe.domain.Status;
import ru.kranbe.domain.crane.Crane;
import ru.kranbe.domain.organization.Organization;
import ru.kranbe.domain.petition.Petition;
import ru.kranbe.domain.user.Profile;
import ru.kranbe.domain.user.Role;
import ru.kranbe.web.dto.validation.OnCreate;
import ru.kranbe.web.dto.validation.OnUpdate;

import java.util.Date;
import java.util.Set;


@Data
@Schema(description = "User DTO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    @NotNull(message = "Login must be not null.",
//            groups = {OnCreate.class, OnUpdate.class})
//    @Length(max = 100,
//            message = "Login length must be smaller than 100 symbols.",
//            groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @Schema(description = "User email", example = "johndoe@gmail.com")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @NotNull(message = "email must be not null.",
            groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 100,
            message = "Email length must be smaller than 100 symbols.",
            groups = {OnCreate.class, OnUpdate.class})
    @Email(message = "Email is not correct DTO")
    private String email;

    @JsonIgnore
    private String password;

    /*@Schema(description = "User password confirmation", example = "12345")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password confirmation must be not null.",
            groups = {OnCreate.class})
    private String passwordConfirmation;*/

    @Schema(description = "User phone", example = "89889889898")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Min(value = 0)
    @Max(value = 11)
    private Long phone;

    // TODO proverki
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Profile profile;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Role> roles;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Organization> organizations;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Petition> petitions;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Crane> cranes;

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


