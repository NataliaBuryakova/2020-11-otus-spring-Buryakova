package ru.otus.task02.exception;

public class PersonCreateException extends Exception {
    public PersonCreateException(String message) {
        super(message);
    }

    public PersonCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
