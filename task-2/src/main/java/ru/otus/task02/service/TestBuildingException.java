package ru.otus.task02.service;

public class TestBuildingException extends Exception {

    public TestBuildingException(String message) {
        super(message);
    }

    public TestBuildingException(String message, Throwable cause) {
        super(message, cause);
    }
}
