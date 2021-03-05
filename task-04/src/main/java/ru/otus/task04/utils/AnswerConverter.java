package ru.otus.task04.utils;

import com.opencsv.bean.AbstractCsvConverter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import ru.otus.task04.domain.Answer;

public class AnswerConverter extends AbstractCsvConverter {

    @Override
    public Object convertToRead(String value) throws CsvDataTypeMismatchException {
        try {
            Answer answer = new Answer();
            if (!value.isEmpty()) {
                String[] split = value.split("\\.", 2);
                answer.setNumber(split[0]);
                answer.setText(split[1]);
            }
            return answer;
        }catch (Exception exc){
            throw new CsvDataTypeMismatchException(exc.getMessage());
        }

    }

    @Override
    public String convertToWrite(Object value) {
        Answer answer = (Answer) value;
        return String.format("%s.%s", answer.getNumber(), answer.getText());
    }

}
