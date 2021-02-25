package ru.otus.task03.service;

public interface MessageSourceService {
    String getMessage(String message);
    String getMessage(String message,Object[] objects);
}
