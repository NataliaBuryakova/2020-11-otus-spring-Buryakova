package ru.otus.task04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.task04.config.AppProps;
import ru.otus.task04.config.WorksheetProps;
import ru.otus.task04.service.TestDialogueServiceImpl;

@SpringBootApplication
@EnableConfigurationProperties({AppProps.class, WorksheetProps.class})
public class TestApplication {

    public static void main(String[] args) {
                SpringApplication.run(TestApplication.class, args);
    }

}
