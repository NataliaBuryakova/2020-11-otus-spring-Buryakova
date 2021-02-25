package ru.otus.task03.service;

public class PersonCreateException extends Exception {
    public PersonCreateException(String message) {
        super(message);
    }

    public PersonCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
