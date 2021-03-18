package ru.otus.task05.dao;

import ru.otus.task05.domain.Author;
import ru.otus.task05.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    long insert(Book book);

    void update(Book book);

    void deleteById(long id);

    Optional<Book> getById(long id);

    List<Book> getAll();


}
