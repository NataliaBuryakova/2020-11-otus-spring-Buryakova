package ru.otus.task03.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.task03.domain.Answer;
import ru.otus.task03.domain.Question;
import ru.otus.task03.domain.Worksheet;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("WorksheetDaoCsv class тест: ")
public class WorksheetDaoCsvTest {
    private Worksheet worksheet;
    private WorksheetDaoCsv worksheetDao;

    @BeforeEach
     void initWorksheet(){
        List<Answer> answers = new ArrayList<>(1);
        answers.add(new Answer());
        worksheet = new Worksheet(new Question("5", "How many letters are in the Russian alphabet?( Write your answer)"),answers,"33");
    }

    @DisplayName("Парсит csv файл")
    @Test
    void getWorksheetCsv() throws Exception {
        worksheetDao = new WorksheetDaoCsv("worksheet.csv");
        List<Worksheet> worksheetList = worksheetDao.getWorksheetList();
        assertThat(worksheetList).hasSize(5)
                                .contains(worksheet);
    }

    @DisplayName("Парсит пустой csv файл")
    @Test
    void getEmptyCsv() throws Exception {
        worksheetDao = new WorksheetDaoCsv("empty.csv");
        List<Worksheet> worksheetList = worksheetDao.getWorksheetList();
        assertTrue(worksheetList.isEmpty());
    }

    @DisplayName("Ошибка парсинга файла")
    @Test
     void whenExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            worksheetDao = new WorksheetDaoCsv("error.csv");
            worksheetDao.getWorksheetList();
        });

        String expectedMessage = "Error parsing CSV ";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
