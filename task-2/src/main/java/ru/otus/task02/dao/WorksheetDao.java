package ru.otus.task02.dao;

import ru.otus.task02.domain.Worksheet;

import java.util.List;

public interface WorksheetDao {
    List<Worksheet> getWorksheetList()  throws WorksheetReadingException;
}
