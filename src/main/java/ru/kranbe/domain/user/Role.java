package ru.kranbe.domain.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.kranbe.domain.BaseEntity;

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(callSuper=false)
public class Role extends BaseEntity {
    @Column(name = "name", nullable = false, updatable = false)
    private String name;
}

