package ru.otus.task01.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import ru.otus.task01.domain.Worksheet;

import java.io.*;
import java.util.List;

public class WorksheetDaoImpl implements WorksheetDao{
    private final String fileName;
    public WorksheetDaoImpl(String fileName) {
        this.fileName = fileName;
    }
    public List<Worksheet> parseCsvToWorksheetList() throws Exception {

            try (InputStreamReader isr = new InputStreamReader(getClass().getResourceAsStream("/" + fileName))) {
                return new CsvToBeanBuilder(isr)
                        .withType(Worksheet.class)
                        .build().parse();
            }
        }



}
