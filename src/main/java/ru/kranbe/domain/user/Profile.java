package ru.kranbe.domain.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.kranbe.domain.BaseEntity;

@Entity
@Table(name = "profiles")
@Data
@EqualsAndHashCode(callSuper=false)
public class Profile extends BaseEntity {
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "avatar")
    private String avatar;
}
