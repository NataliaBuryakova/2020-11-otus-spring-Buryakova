package ru.otus.task06.dao;

import ru.otus.task06.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book save(Book book);
    long insert(Book book);

    void update(Book book);

    void deleteById(long id);

    Optional<Book> findById(long id);

    List<Book> findAll();


}
