package ru.otus.task04.service;

import ru.otus.task04.domain.Person;

public interface TestDialogueService {
    /**
     *  для диалога с неопределенным тестируемым
     */
    void startTest();

    /**
     * Начало тестирования для конкретной персоны
     * @param person
     */
    void startTestFromPerson(Person person);

    /**
     * Запрос идентификации персоны
     * @param name
     * @return
     */
    Person login(String name);
}
