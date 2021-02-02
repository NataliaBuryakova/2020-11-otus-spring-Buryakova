package ru.otus.task02.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.task02.domain.Worksheet;
import ru.otus.task02.exception.WorksheetReadingException;

import java.io.*;
import java.util.List;
@Service
@PropertySource("classpath:application.properties")
public class WorksheetDaoImpl implements WorksheetDao{
    @Value("${csv.worksheet.name}")
    private String fileName;
    @Autowired
    public WorksheetDaoImpl() {
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
