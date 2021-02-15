package ru.otus.task03.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import ru.otus.task03.dao.WorksheetDaoCsv;
import ru.otus.task03.domain.Answer;
import ru.otus.task03.domain.Question;
import ru.otus.task03.domain.Worksheet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@DisplayName("WorksheetServiceImplTest class тест: ")
public class WorksheetServiceImplTest {

    private Worksheet worksheet;
    @Autowired
    private WorksheetServiceImpl worksheetService;
    @MockBean
    private WorksheetDaoCsv worksheetDao;

    @BeforeEach
     void initWorksheet(){
        List<Answer> answers = new ArrayList<>(1);
        answers.add(new Answer("1","60"));
        worksheet = new Worksheet(new Question("1", "How many minutes in an hour?(Put number of answer)"),answers,"1");
    }

    @DisplayName("Генерирует текст вопроса")
    @Test
    void getTextFromQA() {
        String textFromQA = worksheetService.getTextFromQA(worksheet);
        assertTrue(textFromQA.contains("How many minutes"));
    }

    @DisplayName("Введен правильный ответ")
    @Test
    void correctAnswer() {
        assertEquals(true,worksheetService.isCorrectAnswer(worksheet,"1"));
    }

    @DisplayName("Тест сдан")
    @Test
    void getTestResultTextOk() {
        assertTrue(worksheetService.isTestDone(4));
    }

    @DisplayName("Тест не сдан")
    @Test
    void getTestResultTextFail() {
        assertFalse(worksheetService.isTestDone(3));
    }
}
