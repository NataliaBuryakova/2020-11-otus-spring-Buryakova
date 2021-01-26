package ru.otus.task01.utils;

import com.opencsv.bean.AbstractCsvConverter;
import ru.otus.task01.domain.Answer;

public class AnswerConverter extends AbstractCsvConverter {

    @Override
    public Object convertToRead(String value) {
        try {
            Answer answer = new Answer();
            if (!value.isEmpty()) {
                String[] split = value.split("\\.", 2);
                answer.setNumber(split[0]);
                answer.setText(split[1]);
            }
            return answer;
        }catch (Exception exc){
            return new Answer();
        }

    }

    @Override
    public String convertToWrite(Object value) {
        Answer answer = (Answer) value;
        return String.format("%s.%s", answer.getNumber(), answer.getText());
    }

}
