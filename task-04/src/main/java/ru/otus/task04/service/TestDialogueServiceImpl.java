package ru.otus.task04.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.task04.domain.Person;
import ru.otus.task04.domain.Worksheet;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TestDialogueServiceImpl implements TestDialogueService {
    private final WorksheetService worksheetService;
    private final InOutService inOutService;
    private final PersonService personService;
    private final MessageSourceService messageSourceService;


@Override
public Person login (String name){
    try {
        Person person =  personService.createPerson(name);
        inOutService.println(messageSourceService.getMessage("test.welcome",person.getName()));
        return person;
    } catch (PersonCreateException e) {
        inOutService.println(messageSourceService.getMessage("test.load.error"));
        return null;
    }

}

@Override
public void startTestFromPerson (Person person){
    try {
        List<Worksheet> worksheetList = worksheetService.getWorksheetList();
        int correctAnswers = askQuestionsDialogue(worksheetList);
        inOutService.println(
                getTestResultInfo(person,
                        getTestResultText(worksheetList.size(), correctAnswers))
        );
    } catch (TestBuildingException e) {
        inOutService.println(messageSourceService.getMessage("test.load.error"));
    }catch (TestEmptyException e){
        inOutService.println(messageSourceService.getMessage("test.load.empty"));
    }
}
    @Override
    public void startTest()  {
        try  {
            Person person = askPersonName();
            startTestFromPerson(person);
        } catch (PersonCreateException e) {
            inOutService.println(messageSourceService.getMessage("test.load.error"));
        }

    }
   /* public Person getPersonName(String name) {
        try {
            return personService.createPerson(name);
        } catch (PersonCreateException e) {
           return null;
        }

    }*/

    private Person askPersonName () throws PersonCreateException {
        inOutService.println(messageSourceService.getMessage("ask.name"));
        String name = inOutService.read();
        if (name.isEmpty()){
            name = messageSourceService.getMessage("name.guest");
        }
        return login(name);
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
        return messageSourceService.getMessage("test.result.info",person.getName(),testResultText );
    }

    private String getTestResultText(int totalQuestions, int correctAnswers){
        String testResult = worksheetService.isTestDone(correctAnswers)?messageSourceService.getMessage("test.result.success"):messageSourceService.getMessage("test.result.fail");
        return messageSourceService.getMessage("test.result",String.valueOf(correctAnswers),String.valueOf(totalQuestions),testResult );
    }






}
