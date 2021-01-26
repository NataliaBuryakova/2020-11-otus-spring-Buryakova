package ru.otus.task01.dao;

import ru.otus.task01.domain.Worksheet;

import java.io.FileNotFoundException;
import java.util.List;

public interface WorksheetDao {
    List<Worksheet> parseCsvToWorksheetList()  throws FileNotFoundException, Exception;
}
