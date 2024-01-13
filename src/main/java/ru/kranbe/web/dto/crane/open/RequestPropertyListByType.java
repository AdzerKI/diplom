package ru.kranbe.web.dto.crane.open;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(description = "Request crane properties by Type")
public class RequestPropertyListByType {
    @Schema(description = "crane type", example = "WALL_MOUNTED_CANTILEVER_CRANE")
    @NotBlank
    private String type;
}
