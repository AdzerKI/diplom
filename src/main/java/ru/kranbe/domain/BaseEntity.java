package ru.kranbe.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

/**
 * Base class with property 'id'.
 * Used as a base class for all objects that requires this property.
 */

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @CreatedDate
    @Column(name = "created", nullable = false, updatable = false)
    private Date created;

    @LastModifiedDate
    @Column(name = "updated", nullable = false)
    private Date updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    // Автоустановка дат
    @PrePersist
    protected void onCreate() {
        created = new Date();
        updated = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}
