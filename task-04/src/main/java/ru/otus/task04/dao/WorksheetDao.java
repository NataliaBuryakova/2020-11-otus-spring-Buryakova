package ru.otus.task04.dao;

import ru.otus.task04.domain.Worksheet;

import java.util.List;

public interface WorksheetDao {
    List<Worksheet> getWorksheetList()  throws WorksheetReadingException;
}
