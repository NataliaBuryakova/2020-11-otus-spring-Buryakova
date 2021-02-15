package ru.otus.task03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.task03.config.AppProps;
import ru.otus.task03.config.WorksheetProps;
import ru.otus.task03.service.TestDialogueServiceImpl;

@SpringBootApplication
@EnableConfigurationProperties({AppProps.class, WorksheetProps.class})

public class TestApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(TestApplication.class, args);
        var startService = context.getBean(TestDialogueServiceImpl.class);
        startService.startTest();
    }
}
