package ru.otus.task04.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.task04.domain.Answer;
import ru.otus.task04.domain.Question;
import ru.otus.task04.domain.Worksheet;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@ActiveProfiles("test")//иначе подгружается application.yml из основной папки
@DisplayName("WorksheetDaoCsv class тест: ")
public class WorksheetDaoCsvTest {
    private Worksheet worksheet;
    @Autowired
    private WorksheetDaoCsv worksheetDao;

    @BeforeEach
     void initWorksheet(){
        List<Answer> answers = new ArrayList<>(1);
        answers.add(new Answer());
        worksheet = new Worksheet(new Question("5", "How many letters are in the Russian alphabet?( Write your answer)"),answers,"33");
    }

    @DisplayName("Парсит csv файл (название берет из application.yml )")
    @Test
    void getWorksheetCsv() throws Exception {
        List<Worksheet> worksheetList = worksheetDao.getWorksheetList();
        assertThat(worksheetList).hasSize(5)
                                .contains(worksheet);
    }

    @DisplayName("Парсит пустой csv файл")
    @Test
    void getEmptyCsv() throws Exception {
        worksheetDao = new WorksheetDaoCsv("empty.csv");
        List<Worksheet> worksheetList = worksheetDao.getWorksheetList();
        assertTrue(worksheetList.isEmpty());
    }

    @DisplayName("Ошибка парсинга файла")
    @Test
     void whenExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            worksheetDao = new WorksheetDaoCsv("error.csv");
            worksheetDao.getWorksheetList();
        });

        String expectedMessage = "Error parsing CSV ";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
