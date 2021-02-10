package ru.otus.task02.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AppConfig {
    private final int correctAnswersValue;
    private final String fileName;

    public AppConfig(@Value("${test.correct.answers.value}") int correctAnswersValue, @Value("${csv.worksheet.name}")String fileName) {
        this.correctAnswersValue = correctAnswersValue;
        this.fileName = fileName+".csv";
    }


}





