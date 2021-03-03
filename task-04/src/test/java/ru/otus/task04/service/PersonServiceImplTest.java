package ru.otus.task04.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.task04.domain.Person;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DisplayName("PersonServiceImplTest class тест: ")
class PersonServiceImplTest {
    private final static String NAME  = "Aurora";
    @Autowired
    private PersonServiceImpl personService;
    @DisplayName("Создает объект Person")
    @Test
    void createPerson() throws PersonCreateException {
        Person person = personService.createPerson(NAME);
        assertEquals(NAME,person.getName());

    }
}