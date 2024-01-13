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
@Schema(description = "Role DTO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDTO {

    @Schema(description = "Roles list", example = "ROLE_USER")
    @Length(max = 100,
            message = "lastName length must be smaller than 100 symbols.",
            groups = {OnCreate.class, OnUpdate.class})
    private String name;
}
