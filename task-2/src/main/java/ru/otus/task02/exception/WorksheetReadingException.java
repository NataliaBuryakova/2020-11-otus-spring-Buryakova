package ru.otus.task02.exception;

import java.io.IOException;

public class WorksheetReadingException extends Exception {
    public WorksheetReadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
