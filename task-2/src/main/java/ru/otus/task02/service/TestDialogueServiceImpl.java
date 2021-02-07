package ru.otus.task02.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.task02.domain.Person;
import ru.otus.task02.domain.Worksheet;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TestDialogueServiceImpl implements TestDialogueService {
    private final WorksheetService worksheetService;
    private final InOutService inOutService;
    private final PersonService personService;

      @Override
    public void startTest()  {
        String welcome = "Welcome to testing!";
        String errorTestListMsg = "Could not load test";
        String errorTestEmptyMsg = "Test is empty";

        try {
            inOutService.println(welcome);
            List<Worksheet> worksheetList = worksheetService.getWorksheetList();
            Person person = askPersonName();
            int correctAnswers = askQuestionsDialogue(worksheetList );
            inOutService.println(
                    getTestResultInfo(person,
                            worksheetService.getTestResultText(worksheetList.size(), correctAnswers))
            );

        } catch (TestBuildingException e) {
            inOutService.println( errorTestListMsg);
        }catch (TestEmptyException e){
            inOutService.println(errorTestEmptyMsg);
        }
    }
    private Person askPersonName (){
        String enterName = "Enter your name";
        String errorNameMsg = "Could not read name.";
        Person person = null;
        while (person == null){
            try {
                inOutService.println(enterName);
                String name = inOutService.read();
                person = personService.createPerson(name);
            } catch (PersonCreateException e) {
                inOutService.println(errorNameMsg);
            }
        }
        return person;
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
        return String.format("Dear %s! %s", person.getName(), testResultText);
    }





}
