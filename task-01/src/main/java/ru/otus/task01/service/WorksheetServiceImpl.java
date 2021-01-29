package ru.otus.task01.service;

import ru.otus.task01.dao.WorksheetDao;
import ru.otus.task01.domain.Answer;
import ru.otus.task01.domain.Question;
import ru.otus.task01.domain.Worksheet;

import java.util.List;

public class WorksheetServiceImpl implements WorksheetService{
    private final WorksheetDao worksheetDao;

    public WorksheetServiceImpl(WorksheetDao worksheetDao) {
        this.worksheetDao = worksheetDao;
    }

    public String getWorksheetText()  {
       String result;
       try {
           List<Worksheet> worksheetList = worksheetDao.parseCsvToWorksheetList();
           if(!worksheetList.isEmpty()) {
               result = getTextFromWorksheetList(worksheetList);
           }else {
               result = "no questions in file";
           }
       }catch (Exception e){
           result = "failed to get data";
       }

       return result;
    }

    private String getTextFromWorksheetList(List<Worksheet> worksheetList)  {
        StringBuilder questionsText = new StringBuilder(1);
        worksheetList.forEach(worksheet -> questionsText.append(getTextFromWorksheet(worksheet)));
        return questionsText.toString();
    }

    private String getTextFromWorksheet(Worksheet worksheet) {
        StringBuilder worksheetText = new StringBuilder(1);
        worksheetText.append(getTextFromQuestion(worksheet.getQuestion()));
        worksheetText.append(getTextFromAnswers(worksheet.getAnswersText()));
        return worksheetText.toString();
    }

    private String getTextFromQuestion(Question question){
        return question.getNumber() + ". " +
                question.getText() + "\n";
    }

    private String getTextFromAnswers( List<Answer> answers){
        StringBuilder answerText = new StringBuilder(1);
        answers.stream().filter(answer -> answer.getNumber()!=null)
                        .forEach(answer -> answerText.append("\t")
                                                .append(answer.getNumber()).append(". ")
                                                .append(answer.getText()).append("\n"));

        return answerText.toString();
    }
}
