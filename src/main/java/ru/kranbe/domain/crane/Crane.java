package ru.kranbe.domain.crane;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.kranbe.domain.BaseEntity;
import ru.kranbe.domain.crane.enums.*;
import ru.kranbe.domain.user.User;

@Entity
@Table(name = "cranes")
@Data
@EqualsAndHashCode(callSuper = false)
public class Crane extends BaseEntity {
    //TODO
    //@Column(name = "owner_id", nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User ownerId;

    @Column(name = "type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "design")
    @Enumerated(EnumType.STRING)
    private Design design;

    @Column(name = "load_capacity")
    private Float loadCapacity;

    @Column(name = "cantilever_span_one")
    private Float cantileverSpanOne;

    @Column(name = "cantilever_span_two")
    private Float cantileverSpanTwo;

    @Column(name = "cantilever_departure")
    private Float cantileverDeparture;

    @Column(name = "turn_angle")
    private Integer turnAngle;

    @Column(name = "lifting_height")
    private Float liftingHeight;

    @Column(name = "placement_category")
    @Enumerated(EnumType.STRING)
    private PlacementCategory placementCategory;

    @Column(name = "temperature_regime_from")
    private Integer temperatureRegimeFrom;

    @Column(name = "temperature_regime_to")
    private Integer temperatureRegimeTo;

    @Column(name = "operating_regime_group")
    @Enumerated(EnumType.STRING)
    private OperatingRegimeGroup operatingRegimeGroup;

    @Column(name = "cart_lifting_mechanism")
    @Enumerated(EnumType.STRING)
    private CartLiftingMechanism cartLiftingMechanism;

    @Column(name = "is_service_platform")
    private Boolean isServicePlatform;

    @Column(name = "management_type")
    @Enumerated(EnumType.STRING)
    private ManagementType managementType;

    @Column(name = "is_radio_control")
    private Boolean isRadioControl;

    @Column(name = "is_remote_control")
    private Boolean isRemoteControl;

    @Column(name = "is_cabin_control")
    private Boolean isCabinControl;


    @Column(name = "movement_surface")
    @Enumerated(EnumType.STRING)
    private MovementSurface movementSurface;

    @Column(name = "is_without_console")
    private Boolean isWithoutConsole;

    @Column(name = "console")
    private Float console;

    @Column(name = "right_console")
    private Float rightConsole;

    @Column(name = "left_console")
    private Float leftConsole;

    @Column(name = "bridge_gallery")
    @Enumerated(EnumType.STRING)
    private BridgeGallery bridgeGallery;

    @Column(name = "crane_current_line")
    @Enumerated(EnumType.STRING)
    private CraneCurrentLine craneCurrentLine;

    @Column(name = "telpher_current_line")
    @Enumerated(EnumType.STRING)
    private TelpherCurrentLine telpherCurrentLine;

    @Column(name = "is_standard_speed")
    private Boolean isStandardSpeed;

    @Column(name = "lifting_speed")
    private Float liftingSpeed;

    @Column(name = "crane_speed")
    private Float craneSpeed;

    @Column(name = "turn_speed")
    private Float turnSpeed;

    @Column(name = "telpher_speed")
    private Float telpherSpeed;

    @Column(name = "is_lift_break")
    private Boolean isLiftBreak;

    @Column(name = "is_crane_break")
    private Boolean isCraneBreak;

    @Column(name = "is_turn_limiter")
    private Boolean isTurnLimiter;

    @Column(name = "is_telpher_break")
    private Boolean isTelpherBreak;

    @Column(name = "is_frequency_converter")
    private Boolean isFrequencyConverter;

    @Column(name = "is_limit_switches_crane_movement")
    private Boolean isLimitSwitchesCraneMovement;

    @Column(name = "is_limit_switches_turn_console")
    private Boolean isLimitSwitchesTurnConsole;

    @Column(name = "is_limit_switches_telpher_movement")
    private Boolean isLimitSwitchesTelpherMovement;

    @Column(name = "is_limit_switches_telpher_lift")
    private Boolean isLimitSwitchesTelpherLift;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "is_delivery")
    private Boolean isDelivery;

    @Column(name = "installation")
    private Integer installation;

    @Column(name = "beam")
    @Enumerated(EnumType.STRING)
    private Beam beam;

    @Column(name = "other_requirement")
    private String otherRequirement;

    @Column(name = "file")
    private String file;

    @Column(name = "scheme_value_one")
    private Float schemeValueOne;

    @Column(name = "scheme_value_two")
    private Float schemeValueTwo;

    @Column(name = "scheme_value_three")
    private Float schemeValueThree;

    @Column(name = "scheme_value_four")
    private Float schemeValueFour;

    @Column(name = "scheme_value_five")
    private Float schemeValueFive;

    @Column(name = "scheme_value_six")
    private Float schemeValueSix;

    @Column(name = "scheme_value_seven")
    private Float schemeValueSeven;

    @Column(name = "scheme_value_eight")
    private Float schemeValueEight;

    @Column(name = "scheme_value_nine")
    private Float schemeValueNine;

    @Column(name = "scheme_value_ten")
    private Float schemeValueTen;

    @Column(name = "scheme_lifting_height")
    private Float schemeLiftingHeight;

    @Column(name = "scheme_rail_one")
    private String schemeRailOne;

    @Column(name = "scheme_rail_two")
    private String schemeRailTwo;
}
