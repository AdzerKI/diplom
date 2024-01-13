package ru.kranbe.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.kranbe.domain.BaseEntity;
import ru.kranbe.domain.crane.Crane;
import ru.kranbe.domain.organization.Organization;
import ru.kranbe.domain.petition.Petition;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity implements Serializable {
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id", unique = true, nullable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Profile profile;

    @Column(name = "username", unique = true)
    private String username;

    @Email(message = "Email is not correct Model")
    @NotBlank
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "password")
    private String password;
//    @Transient
//    private String passwordConfirmation;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(
                    name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(
                    name = "role_id", referencedColumnName = "id")})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_organizations",
            joinColumns = {@JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            )}, inverseJoinColumns = {@JoinColumn(
                    name = "organization_id", referencedColumnName = "id")})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Organization> organizations;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_petitions",
            joinColumns = {@JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            )}, inverseJoinColumns = {@JoinColumn(
                    name = "petition_id", referencedColumnName = "id")})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Petition> petitions;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_cranes",
            joinColumns = {@JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            )}, inverseJoinColumns = {@JoinColumn(
                    name = "crane_id", referencedColumnName = "id")})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Crane> cranes;
}

