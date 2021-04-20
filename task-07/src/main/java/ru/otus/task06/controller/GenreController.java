package ru.otus.task06.controller;

import org.springframework.stereotype.Service;
import ru.otus.task06.domain.Genre;
import ru.otus.task06.service.GenreService;
import ru.otus.task06.service.InOutService;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
