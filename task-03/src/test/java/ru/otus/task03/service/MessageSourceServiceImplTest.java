package ru.otus.task03.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.task03.config.AppProps;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("MessageSourceServiceImplTest class тест:")
class MessageSourceServiceImplTest {

    @Autowired
    private MessageSourceServiceImpl messageSourceService;

    @DisplayName("Возвращает текст из Bundle")
    @Test
    void getMessageFromBundle() {
        assertEquals("your name:",messageSourceService.getMessage("ask.name"));
    }
}