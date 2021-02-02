package ru.otus.task02.dao;

import ru.otus.task02.domain.Worksheet;
import ru.otus.task02.exception.WorksheetReadingException;

import java.io.FileNotFoundException;
import java.util.List;

public interface WorksheetDao {
    List<Worksheet> getWorksheetList()  throws WorksheetReadingException;
}
