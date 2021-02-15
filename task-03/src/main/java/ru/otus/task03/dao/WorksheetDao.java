package ru.otus.task03.dao;

import ru.otus.task03.domain.Worksheet;

import java.util.List;

public interface WorksheetDao {
    List<Worksheet> getWorksheetList()  throws WorksheetReadingException;
}
