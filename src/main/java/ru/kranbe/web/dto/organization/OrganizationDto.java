package ru.kranbe.web.dto.organization;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.kranbe.domain.Status;
import ru.kranbe.domain.user.User;

import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(description = "Organization DTO")
public class OrganizationDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Идентификатор владельца организации")
    private User ownerId;

    @Schema(description = "Идентификатор пользователя")
    private Long regionId;

    @Schema(description = "электронная почта организации")
    private String email;

    @Schema(description = "номер телефона организации")
    private Long phone;

    @Schema(description = "сайт организации")
    private String site;

    @Schema(description = "полное наименование организации")
    private String organizationFullName;

    @Schema(description = "сокращенное наименование организации")
    private String organizationShortName;

    @Schema(description = "ОГРН или  ОГРНИП")
    private Long ogrn;

    @Schema(description = "ИНН")
    private Long inn;

    @Schema(description = "КПП")
    private Long kpp;

    @Schema(description = "дата создания организации")
    private Date dateOfFoundation;

    @Schema(description = "официальный адрес организации")
    private String legalAddress;

    @Schema(description = "реальный адрес организации")
    private String realAddress;

    @Schema(description = " Фамилия директора")
    private String directorLastName;

    @Schema(description = " имя директора ")
    private String directorFirstName;

    @Schema(description = "отчество директора")
    private String directorPatronymic;

    @Schema(description = "ОКАТО")
    private Long okato;

    @Schema(description = "ОКПО")
    private String okpo;

    @Schema(description = "ОКВЕД")
    private String okved;

    @Schema(description = "банковское имя")
    private String bankName;

    @Schema(description = "расчетный счет")
    private Long payAccount;

    @Schema(description = "корреспондентский счет")
    private Long korrAccount;

    @Schema(description = "БИК")
    private String bik;

    @Schema(description = "оплачен ли аккаунт?")
    private Boolean isPayed;

    @Schema(description = "статус")
    private Status status;

    @Schema(description = "создан")
    private Date created;

    @Schema(description = "обновлен")
    private Date updated;
}
