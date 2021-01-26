package ru.otus.junit.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.task01.dao.WorksheetDaoImpl;
import ru.otus.task01.domain.Worksheet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("WorksheetDaoImpl class тест: ")
public class WorksheetDaoImplTest  {
    private WorksheetDaoImpl worksheetDao;

    @DisplayName("Парсит csv файл")
    @Test
    void getWorksheetCsv() throws Exception {
        worksheetDao = new WorksheetDaoImpl("worksheet.csv");
        List<Worksheet> worksheetList = worksheetDao.parseCsvToWorksheetList();
        assertFalse(worksheetList.isEmpty());

    }
    @DisplayName("Парсит пустой csv файл")
    @Test
    void getEmptyCsv() throws Exception {
        worksheetDao = new WorksheetDaoImpl("empty.csv");
        List<Worksheet> worksheetList = worksheetDao.parseCsvToWorksheetList();
        assertTrue(worksheetList.isEmpty());
    }



}
