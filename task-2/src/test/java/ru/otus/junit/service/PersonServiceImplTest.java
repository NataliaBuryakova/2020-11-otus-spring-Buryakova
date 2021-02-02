package ru.otus.junit.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.task02.domain.Person;
import ru.otus.task02.exception.PersonCreateException;
import ru.otus.task02.service.PersonServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("PersonServiceImplTest class тест: ")
class PersonServiceImplTest {
    @DisplayName("Создает объект Person")
    @Test
    void createPerson() throws PersonCreateException {
        PersonServiceImpl personService = new PersonServiceImpl();
        Person person = personService.createPerson("Aurora");
        assertEquals("Aurora",person.getName());

    }
}