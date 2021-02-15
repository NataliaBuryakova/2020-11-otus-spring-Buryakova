package ru.otus.task03.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.task03.config.AppProps;
import ru.otus.task03.domain.Person;
import ru.otus.task03.domain.Worksheet;

import java.util.List;
import java.util.Scanner;
@RequiredArgsConstructor
@Service
public class TestDialogueServiceImpl implements TestDialogueService {
    private final WorksheetService worksheetService;
    private final InOutService inOutService;
    private final PersonService personService;
    private final MessageSourceService messageSourceService;



    @Override
    public void startTest()  {
        try  {
            inOutService.println(messageSourceService.getMessage("test.welcome"));
            List<Worksheet> worksheetList = worksheetService.getWorksheetList();
            Person person = askPersonName();
            int correctAnswers = askQuestionsDialogue(worksheetList);
            inOutService.println(
                    getTestResultInfo(person,
                            getTestResultText(worksheetList.size(), correctAnswers))
            );

        } catch (TestBuildingException |PersonCreateException e) {
            inOutService.println(messageSourceService.getMessage("test.load.error"));
        }catch (TestEmptyException e){
            inOutService.println(messageSourceService.getMessage("test.load.empty"));
        }

    }

    private Person askPersonName () throws PersonCreateException {
        inOutService.println(messageSourceService.getMessage("ask.name"));
        String name = inOutService.read();
        while (name.isEmpty()){
            inOutService.println(messageSourceService.getMessage("error.name"));
            inOutService.println(messageSourceService.getMessage("ask.name.again"));
        }
        return personService.createPerson(name);
    }

    private int askQuestionsDialogue(List<Worksheet> worksheetList){
        int correctAnswers = 0;
        for(Worksheet worksheet:worksheetList){
            inOutService.println(worksheetService.getTextFromQA(worksheet));
            String answer = inOutService.read();
            if(worksheetService.isCorrectAnswer(worksheet,answer)){
                correctAnswers++;
            }
        }
        return correctAnswers;
    }

    private String getTestResultInfo(Person person, String testResultText){
        return String.format("%s! %s", messageSourceService.getMessage("test.end.name",new String[]{person.getName()}), testResultText);
    }

    private String getTestResultText(int totalQuestions, int correctAnswers){
        String testResult = worksheetService.isTestDone(correctAnswers)?messageSourceService.getMessage("test.result.success"):messageSourceService.getMessage("test.result.fail");
        return String.format("%s. %s.  ",messageSourceService.getMessage("test.result",new String[]{String.valueOf(correctAnswers),String.valueOf(totalQuestions)}),testResult );
    }





}
