package ru.otus.task02.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.otus.task02.dao.WorksheetDao;
import ru.otus.task02.domain.Answer;
import ru.otus.task02.domain.Question;
import ru.otus.task02.domain.Worksheet;
import ru.otus.task02.exception.TestBuildingException;
import ru.otus.task02.exception.TestEmptyException;
import ru.otus.task02.exception.WorksheetReadingException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.properties")
public class WorksheetServiceImpl implements WorksheetService{
    @Value("${test.correct.answers.value}")
    private int correctValue;

    private final WorksheetDao worksheetDao;

    public WorksheetServiceImpl(WorksheetDao worksheetDao)
    {
        this.worksheetDao = worksheetDao;
    }
    @Override
    public List<Worksheet> getWorksheetList() throws TestBuildingException {
        try {
            List<Worksheet> worksheetList = worksheetDao.getWorksheetList();
            if(!worksheetList.isEmpty()) {
                return worksheetList;
            }else {
                throw new TestEmptyException("no questions in file");
            }
        }catch (Exception e){
            throw new TestBuildingException("failed to get data", e);
        }

    }
    @Override
    public String getWorksheetText() throws TestEmptyException, TestBuildingException {
       return getTextFromWorksheetList(getWorksheetList());
    }
    @Override
    public String getTextFromQA(Worksheet worksheet) {
        return String.format("%s%s",getTextFromQuestion(worksheet.getQuestion()),getTextFromAnswers(worksheet.getAnswersText()));

    }
    @Override
    public Boolean isCorrectAnswer(Worksheet worksheet, String answer){
        return worksheet.getCorrectAnswer().equalsIgnoreCase(answer);

    }
    @Override
    public String getTestResultText(int totalQuestions, int correctAnswers){
        String testResult = isTestDone(correctAnswers)?"Test completed successfully":"Test failed";
        return String.format("Results: %s of %s. %s.  ",correctAnswers,totalQuestions,testResult );
    }

    private String getTextFromWorksheetList(List<Worksheet> worksheetList)  {

        return worksheetList.stream().map(this::getTextFromQA).collect(Collectors.joining());
    }
    private String getTextFromQuestion(Question question){
        return String.format("%s. %s \n",question.getNumber(),question.getText());
    }

    private String getTextFromAnswers( List<Answer> answers){
        return answers.stream()
                .filter(answer -> answer.getNumber()!=null)
                .map(this::getTextFromAnswer).collect(Collectors.joining());
    }
    private String getTextFromAnswer(Answer answer){
        return String.format("\t %s. %s\n", answer.getNumber(), answer.getText());
    }
    private Boolean isTestDone(int correctAnswers){
        return  correctAnswers>=correctValue;
    }
}
