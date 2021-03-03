package ru.otus.task04.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.task04.config.AppProps;
import ru.otus.task04.domain.Worksheet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Repository

public class WorksheetDaoCsv implements WorksheetDao{

    private final String fileName;
    @Autowired
    public WorksheetDaoCsv(AppProps appConfig) {
        this.fileName = appConfig.getFileNameCsv();
    }

    public WorksheetDaoCsv(String fileName) {
        this.fileName = fileName;
    }

    public List<Worksheet> getWorksheetList() throws WorksheetReadingException {
        return parseCsvToWorksheetList(fileName);
    }
    private List<Worksheet> parseCsvToWorksheetList(String fileName) throws WorksheetReadingException {

            try (InputStreamReader isr = new InputStreamReader(getClass().getResourceAsStream("/" + fileName))) {
                return new CsvToBeanBuilder(isr)
                        .withType(Worksheet.class)
                        .build().parse();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new WorksheetReadingException("file not found",e);
            }catch (IOException e) {
                e.printStackTrace();
                throw new WorksheetReadingException("Error read file",e);
            }
    }



}
