package ru.otus.task04.service;

import ru.otus.task04.domain.Person;

import java.util.Optional;

public interface PersonService {
    /**
     * Создает пользователя
     * @param name имя
     * @return
     * @throws PersonCreateException
     */
    Person createPerson(String name) throws PersonCreateException;
}
