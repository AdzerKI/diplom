package ru.kranbe.web.dto.crane;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.kranbe.domain.Status;
import ru.kranbe.domain.crane.enums.*;
import ru.kranbe.domain.user.User;

import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(description = "Crane DTO")
public class CraneDTO {
    @Schema(description = "Идентификатор заявки", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Идентификатор подавшего заявку")
    private User ownerId;

    @Schema(description = "Тип крана")
    private Type type;

    @Schema(description = "Дизайн крана")
    private Design design;

    @Schema(description = "Грузоподъемность крана")
    private Float loadCapacity;

    @Schema(description = "Пролёт")
    private Float cantileverSpanOne;

    @Schema(description = "Пролёт, дополнительный")
    private Float cantileverSpanTwo;

    @Schema(description = "Вылет консоли")
    private Float cantileverDeparture;

    @Schema(description = "Угол поворота")
    private Integer turnAngle;

    @Schema(description = "Высота подъема")
    private Float liftingHeight;

    @Schema(description = "Категория размещения")
    private PlacementCategory placementCategory;

    @Schema(description = "Температурный режим от")
    private Integer temperatureRegimeFrom;

    @Schema(description = "Температурный режим до")
    private Integer temperatureRegimeTo;

    @Schema(description = "Группа режима работы")
    private OperatingRegimeGroup operatingRegimeGroup;

    @Schema(description = "Механизм подъема")
    private CartLiftingMechanism cartLiftingMechanism;

    @Schema(description = "Площадка обслуживания")
    private Boolean isServicePlatform;

    @Schema(description = "Тип управления (поворотом / передвижением)")
    private ManagementType managementType;

    @Schema(description = "Тип управления, радиоуправление")
    private Boolean isRadioControl;

    @Schema(description = "Тип управления, подвесной пульт")
    private Boolean isRemoteControl;

    @Schema(description = "Тип управления, из кабины")
    private Boolean isCabinControl;

    @Schema(description = "Передвижение МПУ (поверхность)")
    private MovementSurface movementSurface;

    @Schema(description = "без консолей")
    private Boolean isWithoutConsole;

    @Schema(description = "консоль")
    private Float console;

    @Schema(description = "консоль правая")
    private Float rightConsole;

    @Schema(description = "консоль левая")
    private Float leftConsole;

    @Schema(description = "галерея вдоль моста")
    private BridgeGallery bridgeGallery;

    @Schema(description = "токопровод к крану")
    private CraneCurrentLine craneCurrentLine;

    @Schema(description = "токопровод механизма подъема")
    private TelpherCurrentLine telpherCurrentLine;

    @Schema(description = "Механизмы стандартно")
    private Boolean isStandardSpeed;

    @Schema(description = "Скорость подъема, механизмы")
    private Float liftingSpeed;

    @Schema(description = "Скорость передвиж. крана / МПУ, механизмы")
    private Float craneSpeed;

    @Schema(description = "Скорость поворота, механизмы")
    private Float turnSpeed;

    @Schema(description = "Скорость тельфера / телеги / тали, механизмы")
    private Float telpherSpeed;

    @Schema(description = "тормоз подъема")
    private Boolean isLiftBreak;

    @Schema(description = "тормоз крана")
    private Boolean isCraneBreak;

    @Schema(description = "тормоз поворота")
    private Boolean isTurnLimiter;

    @Schema(description = "тормоз тельфера")
    private Boolean isTelpherBreak;

    @Schema(description = "частотный преобразователь")
    private Boolean isFrequencyConverter;

    @Schema(description = "Концевые переключатели передвиж. крана")
    private Boolean isLimitSwitchesCraneMovement;

    @Schema(description = "Концевые переключатели поворота консоли")
    private Boolean isLimitSwitchesTurnConsole;

    @Schema(description = "Концевые переключатели передвиж. тельфера")
    private Boolean isLimitSwitchesTelpherMovement;

    @Schema(description = "Концевые переключатели подъема тельфера")
    private Boolean isLimitSwitchesTelpherLift;

    @Schema(description = "дополнительные требования, количество")
    private Integer quantity;

    @Schema(description = "дополнительные требования, доставка")
    private Boolean isDelivery;

    @Schema(description = "дополнительные требования, монтаж пусконаладка")
    private Integer installation;

    @Schema(description = "Балка")
    private Beam beam;

    @Schema(description = "прочие требования")
    private String otherRequirement;

    @Schema(description = "прикрепить файл")
    private String file;

    @Schema(description = "Схема, значение 1")
    private Float schemeValueOne;
    @Schema(description = "Схема, значение 2")
    private Float schemeValueTwo;
    @Schema(description = "Схема, значение 3")
    private Float schemeValueThree;
    @Schema(description = "Схема, значение 4")
    private Float schemeValueFour;
    @Schema(description = "Схема, значение 5")
    private Float schemeValueFive;
    @Schema(description = "Схема, значение 6")
    private Float schemeValueSix;
    @Schema(description = "Схема, значение 7")
    private Float schemeValueSeven;
    @Schema(description = "Схема, значение 8")
    private Float schemeValueEight;
    @Schema(description = "Схема, значение 9")
    private Float schemeValueNine;
    @Schema(description = "Схема, значение 10")
    private Float schemeValueTen;
    @Schema(description = "Схема, в/п")
    private Float schemeLiftingHeight;
    @Schema(description = "Схема, рельс 1")
    private String schemeRailOne;
    @Schema(description = "Схема, рельс 2")
    private String schemeRailTwo;

    @Schema(description = "статус", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonIgnore
    private Status status;

    @Schema(description = "создан", accessMode = Schema.AccessMode.READ_ONLY)
    private Date created;

    @Schema(description = "обновлен", accessMode = Schema.AccessMode.READ_ONLY)
    private Date updated;
}
