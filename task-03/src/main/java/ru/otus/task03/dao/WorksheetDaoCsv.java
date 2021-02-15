package ru.otus.task03.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.task03.config.AppProps;
import ru.otus.task03.config.WorksheetProps;
import ru.otus.task03.domain.Worksheet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Repository

public class WorksheetDaoCsv implements WorksheetDao{

    private final String fileName;
    @Autowired
    public WorksheetDaoCsv(AppProps appConfig) {
        this.fileName = appConfig.getFileName()+"_"+appConfig.getLanguage()+".csv";
    }

    public WorksheetDaoCsv(String fileName) {
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
                throw new WorksheetReadingException("file not found",e);
            }catch (IOException e) {
                e.printStackTrace();
                throw new WorksheetReadingException("Error read file",e);
            }
    }



}
