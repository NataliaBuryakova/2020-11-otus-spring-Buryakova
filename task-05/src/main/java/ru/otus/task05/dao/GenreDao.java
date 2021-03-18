package ru.otus.task05.dao;

import ru.otus.task05.domain.Author;
import ru.otus.task05.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    long insert(Genre genre);

    void update(Genre genre);

    void deleteById(long id);

    Optional<Genre> getById(long id);

    Optional<Genre> getByKind(String kind);

    List<Genre> getAll();


}
