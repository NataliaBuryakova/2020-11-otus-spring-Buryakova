package ru.otus.task05.service;

import ru.otus.task05.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    long create(String name);

    void update(Author author);

    void deleteById(long id);

    Optional<Author> getById(long id);

    Optional<Author> getByName(String name);

    Author getOrCreateByName(String name);

    List<Author> getAll();
}
