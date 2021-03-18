package ru.otus.task05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import ru.otus.task05.domain.Author;
import ru.otus.task05.service.AuthorService;
import ru.otus.task05.service.InOutService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorController {
    private final InOutService inOutService;
    private final AuthorService authorService;
    public AuthorController(InOutService inOutService,  AuthorService authorService) {
        this.inOutService = inOutService;
        this.authorService = authorService;
    }


    public void printList(){
        List<Author> list = authorService.getAll();
        inOutService.println(String.format("Список авторов: \n%s", getListDescription(list)));
    }
    public void deleteAuthorById(long id){
        authorService.deleteById(id);
        inOutService.println("Автор успешно удален");
    }
    private String getDescription(Author author){
        return String.format("- Идентификатор автора: %s \n  Имя автора: %s\n",
                author.getId(), author.getName());
    }
    private String getListDescription(List<Author> list){
        return list.stream()
                .map(this::getDescription).collect(Collectors.joining());
    }

}
