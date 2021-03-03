package ru.otus.task04.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@ActiveProfiles("test")
@DisplayName("MessageSourceServiceImplTest class тест:")
class MessageSourceServiceImplTest {

    @Autowired
    private MessageSourceServiceImpl messageSourceService;

    @DisplayName("Возвращает текст из Bundle для en локали")
    @Test
    void getMessageFromBundle() {
        assertEquals("your name:",messageSourceService.getMessage("ask.name"));
    }
}