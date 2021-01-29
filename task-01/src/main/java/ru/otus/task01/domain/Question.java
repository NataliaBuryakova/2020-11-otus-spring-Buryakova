package ru.otus.task01.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Question {
    @CsvBindByName(column = "number")
    private String number;
    @CsvBindByName(column = "questionText")
    private String text;


}
