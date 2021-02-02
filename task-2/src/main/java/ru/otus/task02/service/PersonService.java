package ru.otus.task02.service;

import ru.otus.task02.domain.Person;
import ru.otus.task02.exception.PersonCreateException;

public interface PersonService {
    /**
     * Создает пользователя
     * @param name имя
     * @return
     * @throws PersonCreateException
     */
    Person createPerson(String name) throws PersonCreateException;
}
