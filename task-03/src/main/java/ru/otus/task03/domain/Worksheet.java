package ru.otus.task03.domain;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;
import lombok.*;
import ru.otus.task03.utils.AnswerConverter;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Worksheet {

    @CsvRecurse
    private Question question;
    @CsvBindAndSplitByName(elementType = Answer.class, splitOn = ";", converter = AnswerConverter.class)
    private List<Answer> answersText;
    @CsvBindByName
    private String correctAnswer;

}
