package ru.otus.task02.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Question {
    @CsvBindByName(column = "number")
    private String number;
    @CsvBindByName(column = "questionText")
    private String text;


}
