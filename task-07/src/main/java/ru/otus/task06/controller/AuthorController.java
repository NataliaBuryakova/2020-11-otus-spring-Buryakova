package ru.otus.task06.controller;

import org.springframework.stereotype.Service;
import ru.otus.task06.domain.Author;
import ru.otus.task06.service.AuthorService;
import ru.otus.task06.service.InOutService;

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
    public void printInfoById(long id){
        String info = "Информация об авторе:";
        authorService.getById(id).ifPresentOrElse(
                (author) ->
                {inOutService.println(
                        String.format(info + "\n%s"
                                ,getDescription(author)));
                },
                () ->
                {inOutService.println(
                        String.format(info + "\n%s"
                                ,"не найдена"));
                }
        );
    }

    public void printList(){
        List<Author> list = authorService.getAll();
        inOutService.println(String.format("Список авторов: \n%s", getListDescription(list)));
    }
    public void deleteAuthorById(long id){
        authorService.deleteById(id);
        inOutService.println("Автор успешно удален");
    }

    public void createAuthor(String name){
        authorService.create(name);
        inOutService.println("Автор успешно создан");
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
