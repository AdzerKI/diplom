package ru.kranbe.domain.organization;

import jakarta.persistence.*;
import lombok.*;
import ru.kranbe.domain.BaseEntity;
import ru.kranbe.domain.user.User;

import java.util.Date;

@Entity
@Table(name = "organizations")
@Data
@EqualsAndHashCode(callSuper = false)
public class Organization extends BaseEntity {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User ownerId;

    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "site")
    private String site;

    @Column(name = "organization_full_name", nullable = false )
    private String organizationFullName;

    @Column(name = "organization_short_name", nullable = false)
    private String organizationShortName;

    @Column(name = "ogrn", unique = true)
    private Long ogrn;

    @Column(name = "inn", unique = true)
    private Long inn;

    @Column(name = "kpp")
    private Long kpp;

    @Column(name = "date_of_foundation")
    private Date dateOfFoundation;

    @Column(name = "legal_address")
    private String legalAddress;

    @Column(name = "real_address")
    private String realAddress;

    @Column(name = "director_last_name")
    private String directorLastName;

    @Column(name = "director_first_name")
    private String directorFirstName;

    @Column(name = "director_patronymic")
    private String directorPatronymic;

    @Column(name = "okato")
    private Long okato;

    @Column(name = "okpo")
    private String okpo;

    @Column(name = "okved")
    private String okved;

    @Column(name = "bank_name")
    private String bankName;

    // Расчетный счет
    @Column(name = "pay_account", unique = true)
    private Long payAccount;

    @Column(name = "korr_account")
    private Long korrAccount;

    @Column(name = "bik")
    private String bik;

    // Оплачен ли доступ
    @Column(name = "is_payed")
    private Boolean isPayed;
}
