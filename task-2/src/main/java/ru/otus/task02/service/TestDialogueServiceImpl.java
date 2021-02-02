package ru.otus.task02.service;

import org.springframework.stereotype.Service;
import ru.otus.task02.domain.Person;
import ru.otus.task02.domain.Worksheet;
import ru.otus.task02.exception.PersonCreateException;
import ru.otus.task02.exception.TestBuildingException;
import ru.otus.task02.exception.TestEmptyException;

import java.util.List;
import java.util.Scanner;

@Service
public class TestDialogueServiceImpl implements TestDialogueService {
    private final WorksheetService worksheetService;
    private final InOutService inOutService;
    private final PersonService personService;

    public TestDialogueServiceImpl(WorksheetServiceImpl worksheetService, InOutService inOutService, PersonService personService) {
        this.worksheetService = worksheetService;
        this.inOutService = inOutService;
        this.personService = personService;
    }
    @Override
    public void startTest()  {
        String welcome = "Welcome to testing!";
        String errorTestListMsg = "Could not load test";
        Scanner scanner = inOutService.initScanner();
        try (scanner) {
            inOutService.println(welcome);
            List<Worksheet> worksheetList = worksheetService.getWorksheetList();
            Person person = askPersonName(scanner);
            int correctAnswers = askQuestionsDialogue(worksheetList, scanner);
            inOutService.println(
                    getTestResultInfo(person,
                            worksheetService.getTestResultText(worksheetList.size(), correctAnswers))
            );

        } catch (TestBuildingException | TestEmptyException e) {
            inOutService.println(String.format("%s: %s", errorTestListMsg, e.getMessage()));
        }
    }
    private Person askPersonName (Scanner scanner){
        String enterName = "Enter your name or enter Q to exit";
        String errorNameMsg = "Could not read name.";
        Person person = null;
        while (person == null){
            try {
                inOutService.println(enterName);
                String s = inOutService.read(scanner);
                if (s.equalsIgnoreCase("q")){
                    System.exit(0);
                }
                person = personService.createPerson(s);
            } catch (PersonCreateException e) {
                inOutService.println(errorNameMsg);
            }
        }
        return person;
    }
    private int askQuestionsDialogue(List<Worksheet> worksheetList, Scanner scanner){
        int correctAnswers = 0;
        for(Worksheet worksheet:worksheetList){
            inOutService.println(worksheetService.getTextFromQA(worksheet));
            String answer = inOutService.read(scanner);
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
