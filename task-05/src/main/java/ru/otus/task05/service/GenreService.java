package ru.otus.task05.service;

import ru.otus.task05.domain.Author;
import ru.otus.task05.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    long create(String name);

    void update(Genre genre);

    void deleteById(long id);

    Optional<Genre> getById(long id);

    Optional<Genre> getByKind(String kind);

    Genre getOrCreateByKind(String kind);

    List<Genre> getAll();
}
