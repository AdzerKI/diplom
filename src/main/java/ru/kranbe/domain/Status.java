package ru.kranbe.domain;

/**
 * Enumeration that represents status of domain objects - ACTIVE, DELETED, etc.
 *
 */

public enum Status {
    ACTIVE, // стандартный статус
    NOT_ACTIVE, // емейл не подтвержден
    ARCHIVE, // в архиве
    BANNED, // заблокирован
    DELETED // удален (по желанию пользователя)
}