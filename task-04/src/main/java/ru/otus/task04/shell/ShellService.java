package ru.otus.task04.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.task04.domain.Person;
import ru.otus.task04.service.TestDialogueService;


@ShellComponent
public class ShellService {
    private final TestDialogueService testDialogueService;
    private Person person;
    @Autowired
    public ShellService(TestDialogueService testDialogueService) {
        this.testDialogueService = testDialogueService;
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public void login(@ShellOption(defaultValue = "AnyUser") String userName) {
        this.person = testDialogueService.login(userName);
    }
    @ShellMethod(key = "start",value ="start test")
    public void startApp(){
        testDialogueService.startTestFromPerson(person);
    }


}
