package ru.kranbe.domain.exception;

public class ResourceAlreadyExistException  extends RuntimeException {
    public ResourceAlreadyExistException(String message) {
        super(message);
    }
}
