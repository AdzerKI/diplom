CREATE TABLE IF NOT EXISTS users
(
    id         BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    profile_id BIGINT       NOT NULL UNIQUE,
    username   VARCHAR(100) UNIQUE,
    email      VARCHAR(100) NOT NULL UNIQUE,
    phone      VARCHAR(15) UNIQUE,
    password   VARCHAR(255) NOT NULL,
    status     VARCHAR(25)  NOT NULL DEFAULT 'NOT_ACTIVE',
    created    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP()
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS profiles
(
    id         BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    last_name  VARCHAR(100) NULL,
    first_name VARCHAR(100) NULL,
    patronymic VARCHAR(100) NULL,
    avatar     VARCHAR(255) NULL,
    status     VARCHAR(25)  NOT NULL DEFAULT 'NOT_ACTIVE',
    created    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP()
# TODO не знаю как профиль связать фк если profile_id обязателен
#     constraint fk_profiles_users foreign key (id) references users (profile_id) on delete cascade on update no action
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS roles
(
    id      BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(60) NOT NULL UNIQUE,
    status  VARCHAR(25) NOT NULL DEFAULT 'ACTIVE',
    created TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP()
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO roles (name)
VALUES ('ROLE_USER');
INSERT INTO roles (name)
VALUES ('ROLE_ADMIN');

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    primary key (user_id, role_id),
    constraint fk_users_roles_users foreign key (user_id) references users (id) on delete cascade on update no action,
    constraint fk_users_roles_roles foreign key (role_id) references roles (id) on delete cascade on update no action
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS organizations;
CREATE TABLE organizations
(
    id                      BIGINT PRIMARY KEY AUTO_INCREMENT,
    owner_id                BIGINT       NOT NULL,
    region_id               BIGINT       NULL,
    email                   VARCHAR(100) NULL,
    phone                   BIGINT       NULL,
    site                    VARCHAR(100) NULL,
    organization_full_name  VARCHAR(500) NULL,
    organization_short_name VARCHAR(255) NULL,
    ogrn                    BIGINT       NULL UNIQUE,
    inn                     BIGINT       NULL UNIQUE,
    kpp                     BIGINT       NULL,
    date_of_foundation      DATE         NULL,
    legal_address           VARCHAR(500) NULL,
    real_address            VARCHAR(500) NULL,
    director_last_name      VARCHAR(50)  NULL,
    director_first_name     VARCHAR(50)  NULL,
    director_patronymic     VARCHAR(50)  NULL,
    okato                   BIGINT       NULL,
    okpo                    VARCHAR(20)  NULL,
    okved                   VARCHAR(255) NULL,
    bank_name               VARCHAR(255) NULL,
    pay_account             BIGINT       NULL,
    korr_account            BIGINT       NULL,
    bik                     VARCHAR(20)  NULL,
    is_payed                TINYINT(1)   NOT NULL DEFAULT 0,
    status                  VARCHAR(25)  NOT NULL DEFAULT 'NOT_ACTIVE',
    created                 TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated                 TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    constraint fk_organizations_users foreign key (owner_id) references users (id) on delete cascade on update no action
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS users_organizations;
CREATE TABLE users_organizations
(
    user_id         BIGINT NOT NULL,
    organization_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, organization_id),
    constraint fk_users_organizations_users foreign key (user_id) references users (id),
    constraint fk_users_organizations_organizations foreign key (organization_id) references organizations (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS petitions;
CREATE TABLE petitions
(
    id                BIGINT PRIMARY KEY AUTO_INCREMENT,
    submitter_id      BIGINT       NOT NULL,
    operator_id       BIGINT       NULL,
    type              VARCHAR(50)  NOT NULL,
    topic             VARCHAR(255) NULL,
    description       VARCHAR(255) NULL,
    added_file_list   VARCHAR(100) NULL,
    operator_decision VARCHAR(255) NULL,
    issue_date        TIMESTAMP    NULL,
    issue_result      TINYINT(1)   NULL,
    petition_status   VARCHAR(50)  NULL,
    status            VARCHAR(25)  NOT NULL DEFAULT 'NOT_ACTIVE',
    created           TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated           TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    constraint fk_petitions_users foreign key (submitter_id) references users (id) on delete cascade on update no action,
    constraint fk_petitions_users_users foreign key (operator_id) references users (id) on delete cascade on update no action
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
    COMMENT ='id
user_id - обратившийся пользователь
operator_id - выполнивший оператор
petition_type - ошибка = 1, предложение = 2
petition_topic - тема обращения
petition_description - описание обращения
petition_files - прикрепленные файлы (может быть несколько, цифра через запятую)
petition_status - на рассмотрении = 1, отклонено = 2, одобрено к внедрению = 3, исправлено = 4
decision_operator - решение оператора
decision_admin - решение админа
issue_date - время закрытия
issue_result - создано = 1, в работе = 2, выполнено = 3, отказано = 4';


DROP TABLE IF EXISTS users_petitions;
CREATE TABLE users_petitions
(
    user_id     BIGINT NOT NULL,
    petition_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, petition_id),
    constraint fk_users_petitions_users foreign key (user_id) references users (id),
    constraint fk_users_petitions_petitions foreign key (petition_id) references petitions (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS petitions_messages;
CREATE TABLE petitions_messages
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id     BIGINT      NOT NULL,
    petition_id BIGINT      NOT NULL,
    operator_id BIGINT      NOT NULL,
    status      VARCHAR(25) NOT NULL DEFAULT 'NOT_ACTIVE',
    created     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    constraint fk_petitions_messages_users foreign key (user_id) references users (id) on delete cascade on update no action,
    constraint fk_petitions_messages_petitions foreign key (petition_id) references petitions (id) on delete cascade on update no action,
    constraint fk_petitions_messages_users_users foreign key (operator_id) references users (id) on delete cascade on update no action
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS cranes;
CREATE TABLE cranes
(
    id                                 BIGINT PRIMARY KEY AUTO_INCREMENT,
    owner_id                           BIGINT        NOT NULL,
    type                               VARCHAR(100)  NOT NULL,
    design                             VARCHAR(50)   NULL,
    load_capacity                      DECIMAL(4, 1) NULL,
    cantilever_span_one                DECIMAL(4, 2) NULL,
    cantilever_span_two                DECIMAL(4, 2) NULL,
    cantilever_departure               DECIMAL(3, 1) NULL,
    turn_angle                         SMALLINT      NULL,
    lifting_height                     DECIMAL(4, 1) NULL,
    placement_category                 VARCHAR(50)   NULL,
    temperature_regime_from            TINYINT(3)    NULL,
    temperature_regime_to              TINYINT(3)    NULL,
    operating_regime_group             VARCHAR(50)   NULL,
    cart_lifting_mechanism             VARCHAR(50)   NULL,
    is_service_platform                TINYINT(3)    NULL,
    management_type                    VARCHAR(50)   NULL,
    is_radio_control                   TINYINT(3)    NULL,
    is_remote_control                  TINYINT(3)    NULL,
    is_cabin_control                   TINYINT(3)    NULL,
    movement_surface                   VARCHAR(50)   NULL,
    is_without_console                 TINYINT(3)    NULL,
    console                            DECIMAL(3, 1) NULL,
    right_console                      DECIMAL(3, 1) NULL,
    left_console                       DECIMAL(3, 1) NULL,
    bridge_gallery                     VARCHAR(50)   NULL,
    crane_current_line                 VARCHAR(50)   NULL,
    telpher_current_line               VARCHAR(50)   NULL,
    is_standard_speed                  TINYINT(3)    NULL,
    lifting_speed                      DECIMAL(3, 1) NULL,
    crane_speed                        DECIMAL(3, 1) NULL,
    turn_speed                         DECIMAL(3, 1) NULL,
    telpher_speed                      DECIMAL(3, 1) NULL,
    is_lift_break                      TINYINT(3)    NULL,
    is_crane_break                     TINYINT(3)    NULL,
    is_turn_limiter                    TINYINT(3)    NULL,
    is_telpher_break                   TINYINT(3)    NULL,
    is_frequency_converter             TINYINT(3)    NULL,
    is_limit_switches_crane_movement   TINYINT(3)    NULL,
    is_limit_switches_turn_console     TINYINT(3)    NULL,
    is_limit_switches_telpher_movement TINYINT(3)    NULL,
    is_limit_switches_telpher_lift     TINYINT(3)    NULL,
    quantity                           TINYINT(3)    NULL,
    is_delivery                        TINYINT(3)    NULL,
    installation                       TINYINT(3)    NULL,
    beam                               VARCHAR(50)   NULL,
    other_requirement                  VARCHAR(1000) NULL,
    file                               VARCHAR(255)  NULL,
    scheme_value_one                   FLOAT         NULL,
    scheme_value_two                   FLOAT         NULL,
    scheme_value_three                 FLOAT         NULL,
    scheme_value_four                  FLOAT         NULL,
    scheme_value_five                  FLOAT         NULL,
    scheme_value_six                   FLOAT         NULL,
    scheme_value_seven                 FLOAT         NULL,
    scheme_value_eight                 FLOAT         NULL,
    scheme_value_nine                  FLOAT         NULL,
    scheme_value_ten                   FLOAT         NULL,
    scheme_rail_one                    VARCHAR(255)  NULL,
    scheme_rail_two                    VARCHAR(255)  NULL,
    status                             VARCHAR(25)   NOT NULL DEFAULT 'NOT_ACTIVE',
    created                            TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated                            TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    constraint fk_cranes_users foreign key (owner_id) references users (id) on delete cascade on update no action
);


DROP TABLE IF EXISTS users_cranes;
CREATE TABLE users_cranes
(
    user_id  BIGINT NOT NULL,
    crane_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, crane_id),
    constraint fk_users_cranes_users foreign key (user_id) references users (id),
    constraint fk_users_cranes_cranes foreign key (crane_id) references cranes (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


# TODO constraint
DROP TABLE IF EXISTS files;
CREATE TABLE files
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    path    VARCHAR(255) NOT NULL,
    status  VARCHAR(25)  NOT NULL DEFAULT 'NOT_ACTIVE',
    created TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP()
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS verifications;
CREATE TABLE verifications
(
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    email               VARCHAR(100) NOT NULL UNIQUE,
    token               VARCHAR(255) NOT NULL,
    expire              TIMESTAMP    NOT NULL DEFAULT (CURRENT_TIMESTAMP() + INTERVAL 1 DAY),
    verification_status VARCHAR(25)  NOT NULL,
    status              VARCHAR(25)  NOT NULL DEFAULT 'ACTIVE',
    created             TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated             TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP()
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
