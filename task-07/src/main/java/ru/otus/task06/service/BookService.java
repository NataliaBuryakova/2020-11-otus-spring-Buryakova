package ru.otus.task06.service;

import ru.otus.task06.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    long create(Book bookForCreate);

    void update(Book bookForUpdate);

    void deleteById(long id);

    Optional<Book> getById(long id);

    List<Book> getAll();
}
