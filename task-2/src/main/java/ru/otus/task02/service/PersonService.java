package ru.otus.task02.service;

import ru.otus.task02.domain.Person;

public interface PersonService {
    /**
     * Создает пользователя
     * @param name имя
     * @return
     * @throws PersonCreateException
     */
    Person createPerson(String name) throws PersonCreateException;
}
