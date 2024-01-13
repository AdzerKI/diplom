package ru.kranbe.domain.user.verification;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.kranbe.domain.BaseEntity;

import java.util.Date;

@Entity
@Table(name = "verifications")
@Data
@EqualsAndHashCode(callSuper = false)
public class VerificationEntity extends BaseEntity {
    @Email(message = "Email is not correct Model")
    @NotBlank
    @Column(name = "email", unique = true, nullable = false, updatable = false)
    private String email;

    @Column(name = "token", unique = true, nullable = false, updatable = false)
    private String token;

    @Column(name = "expire", nullable = false, updatable = false)
    private Date expire;

    @Column(name = "verification_status", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Type type;
}
