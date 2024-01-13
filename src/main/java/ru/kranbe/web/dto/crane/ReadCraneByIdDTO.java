package ru.kranbe.web.dto.crane;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "Request for Crane by ID")
public class ReadCraneByIdDTO {
    @Schema(description = "Crane ID", example = "17")
    private Long id;
}