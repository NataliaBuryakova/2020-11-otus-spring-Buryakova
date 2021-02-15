package ru.otus.task03.service;

import ru.otus.task03.domain.Person;

public interface PersonService {
    /**
     * Создает пользователя
     * @param name имя
     * @return
     * @throws PersonCreateException
     */
    Person createPerson(String name) throws PersonCreateException;
}
