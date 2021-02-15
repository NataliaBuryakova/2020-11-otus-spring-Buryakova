package ru.otus.task03.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.task03.domain.Person;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("PersonServiceImplTest class тест: ")
class PersonServiceImplTest {
    private final static String NAME  = "Aurora";
    @DisplayName("Создает объект Person")
    @Test
    void createPerson() throws PersonCreateException {
        PersonServiceImpl personService = new PersonServiceImpl();
        Person person = personService.createPerson(NAME);
        assertEquals(NAME,person.getName());

    }
}