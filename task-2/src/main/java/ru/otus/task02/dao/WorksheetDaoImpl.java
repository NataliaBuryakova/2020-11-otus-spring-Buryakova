package ru.otus.task02.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.task02.config.AppConfig;
import ru.otus.task02.domain.Worksheet;

import java.io.*;
import java.util.List;
@Repository

public class WorksheetDaoImpl implements WorksheetDao{

    private String fileName;
    @Autowired
    public WorksheetDaoImpl(AppConfig appConfig) {
        this.fileName = appConfig.getFileName();
    }
    public WorksheetDaoImpl(String fileName) {
        this.fileName = fileName;
    }

    public List<Worksheet> getWorksheetList() throws WorksheetReadingException {
        return parseCsvToWorksheetList();
    }
    private List<Worksheet> parseCsvToWorksheetList() throws WorksheetReadingException {

            try (InputStreamReader isr = new InputStreamReader(getClass().getResourceAsStream("/" + fileName))) {
                return new CsvToBeanBuilder(isr)
                        .withType(Worksheet.class)
                        .build().parse();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new WorksheetReadingException("Файл не найден",e);
            }catch (IOException e) {
                e.printStackTrace();
                throw new WorksheetReadingException("Ошибка чтения файла",e);
            }
    }



}
