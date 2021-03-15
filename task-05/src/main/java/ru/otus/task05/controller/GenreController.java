package ru.otus.task05.controller;

import org.springframework.stereotype.Controller;
import ru.otus.task05.domain.Genre;
import ru.otus.task05.service.GenreService;
import ru.otus.task05.service.InOutService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GenreController {
    private final InOutService inOutService;
    private final GenreService genreService;
    public GenreController(InOutService inOutService, GenreService genreService) {
        this.inOutService = inOutService;
        this.genreService = genreService;
    }


    public void printList(){
        List<Genre> list = genreService.getAll();
        inOutService.println(String.format("Список жанров: \n%s", getListDescription(list)));
    }
    private String getDescription(Genre genre){
        return String.format("- Идентификатор жанра: %s \n  Название жанра: %s\n",
                genre.getId(), genre.getKind());
    }
    private String getListDescription(List<Genre> list){
        return list.stream()
                .map(this::getDescription).collect(Collectors.joining());
    }
}
