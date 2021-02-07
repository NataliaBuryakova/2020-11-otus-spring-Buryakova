package ru.otus.junit.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.task02.dao.WorksheetDaoImpl;
import ru.otus.task02.domain.Answer;
import ru.otus.task02.domain.Question;
import ru.otus.task02.domain.Worksheet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("WorksheetDaoImpl class тест: ")
public class WorksheetDaoImplTest  {
    private Worksheet worksheet;
    private WorksheetDaoImpl worksheetDao;

    @BeforeEach
     void initWorksheet(){
        List<Answer> answers = new ArrayList<>(1);
        answers.add(new Answer());
        worksheet = new Worksheet(new Question("5", "How many letters are in the Russian alphabet?( Write your answer)"),answers,"33");
    }

    @DisplayName("Парсит csv файл")
    @Test
    void getWorksheetCsv() throws Exception {
        worksheetDao = new WorksheetDaoImpl("worksheet.csv");
        List<Worksheet> worksheetList = worksheetDao.getWorksheetList();
        assertTrue(worksheetList.contains(worksheet));




    }
    @DisplayName("Парсит пустой csv файл")
    @Test
    void getEmptyCsv() throws Exception {
        worksheetDao = new WorksheetDaoImpl("empty.csv");
        List<Worksheet> worksheetList = worksheetDao.getWorksheetList();
        assertTrue(worksheetList.isEmpty());
    }
    @DisplayName("Ошибка парсинга файла")
    @Test
     void whenExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            worksheetDao = new WorksheetDaoImpl("error.csv");
            worksheetDao.getWorksheetList();
        });

        String expectedMessage = "Error parsing CSV ";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }



}
