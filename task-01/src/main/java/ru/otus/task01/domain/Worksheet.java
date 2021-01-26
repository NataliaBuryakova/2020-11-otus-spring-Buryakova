package ru.otus.task01.domain;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.otus.task01.utils.AnswerConverter;

import java.util.List;

@Getter
@Setter
@ToString
public class Worksheet {

    @CsvRecurse
    private Question question;
    @CsvBindAndSplitByName(elementType = Answer.class, splitOn = ";", converter = AnswerConverter.class)
    private List<Answer> answersText;
    @CsvBindByName
    private String correctAnswerNumber;

}
