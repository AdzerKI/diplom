package ru.kranbe.domain.petition;

public enum PetitionStatus {
    PENDING, //на рассмотрении
    REJECTED, // отклонено
    TODO, // одобрено
    DONE, // исправлено
    DELETED // удалено за нарушения
}
