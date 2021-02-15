package ru.otus.task03.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.task03.config.WorksheetProps;
import ru.otus.task03.dao.WorksheetDao;
import ru.otus.task03.domain.Answer;
import ru.otus.task03.domain.Question;
import ru.otus.task03.domain.Worksheet;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class WorksheetServiceImpl implements WorksheetService{

    private final int currectAnswersValue;
    private final WorksheetDao worksheetDao;

    @Autowired
    public WorksheetServiceImpl(WorksheetDao worksheetDao, WorksheetProps worksheetProps)
    {
        this.worksheetDao = worksheetDao;
        this.currectAnswersValue = worksheetProps.getAnswers();
    }

    public WorksheetServiceImpl(WorksheetDao worksheetDao, int currectAnswersValue) {
        this.currectAnswersValue = currectAnswersValue;
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
    public Boolean isTestDone(int correctAnswers){
        return  currectAnswersValue>0 && correctAnswers>=currectAnswersValue;
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

}
