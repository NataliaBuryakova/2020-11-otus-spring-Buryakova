package ru.otus.junit.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import ru.otus.task02.dao.WorksheetDaoImpl;
import ru.otus.task02.domain.Answer;
import ru.otus.task02.domain.Question;
import ru.otus.task02.domain.Worksheet;
import ru.otus.task02.service.WorksheetServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("WorksheetServiceImplTest class тест: ")
//@TestPropertySource(locations = "classpath:application-test.properties") TODO разобраться с использованием application-test.properties
public class WorksheetServiceImplTest {

    private static Worksheet worksheet;

    private WorksheetServiceImpl worksheetService;
    @Mock
    WorksheetDaoImpl worksheetDao;

    @BeforeAll
    static void initWorksheet(){
        List<Answer> answers = new ArrayList<>(1);
        answers.add(new Answer("1","60"));
        worksheet = new Worksheet(new Question("1", "How many minutes in an hour?(Put number of answer)"),answers,"1");
    }


    @DisplayName("Генерирует текст вопроса")
    @Test
    void getTextFromQA() {
        worksheetService = new WorksheetServiceImpl(worksheetDao);
        String textFromQA = worksheetService.getTextFromQA(worksheet);
        assertTrue(textFromQA.contains("How many minutes"));
    }
    @DisplayName("Введен правильный ответ")
    @Test
    void correctAnswer() {
        worksheetService = new WorksheetServiceImpl(worksheetDao);
        assertEquals(true,worksheetService.isCorrectAnswer(worksheet,"1"));
    }
  /*  @DisplayName("Тест сдан")
    @Test
    void getTestResultTextOk() {
        worksheetService = new WorksheetServiceImpl(worksheetDao);
        String textTestResult = worksheetService.getTestResultText(5,4);
        assertTrue(textTestResult.contains("Test completed successfully."));
    }
    @DisplayName("Тест не сдан")
    @Test
    void getTestResultTextFail() {
        worksheetService = new WorksheetServiceImpl(worksheetDao);
        String textTestResult = worksheetService.getTestResultText(5,3);
        assertTrue(textTestResult.contains("Test failed"));
    }*/
}
