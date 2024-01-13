package ru.kranbe.web.dto.petition;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.kranbe.domain.Status;
import ru.kranbe.domain.petition.IssueResult;
import ru.kranbe.domain.petition.PetitionStatus;
import ru.kranbe.domain.petition.Type;
import ru.kranbe.domain.user.User;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(description = "Petition DTO")
public class PetitionDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "идентификатор пользователя отправившей обращение")
    private User submitterId;

    @Schema(description = "идентификатор оператора")
    private User operatorId;

    @Schema(description = "тип обращения")
    private Type type;

    @Schema(description = "название темы обращения")
    private String topic;

    @Schema(description = "тело темы обращения")
    private String description;

    @Schema(description = "прикрепленные файлы")
    private String addedFileList;

    @Schema(description = "описание оператора")
    private String operatorDecision;

    @Schema(description = "дата решения")
    private LocalDateTime issueDate;

    @Schema(description = "результат рассмотрения обращения")
    private IssueResult issueResult;

    @Schema(description = "статус обращения")
    private PetitionStatus petitionStatus;

    @Schema(description = "статус")
    private Status status;

    @Schema(description = "создан")
    private Date created;

    @Schema(description = "обновлен")
    private Date updated;
}
