package ru.otus.task05.dao;

import ru.otus.task05.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    long insert(Author author);

    void update(Author author);

    void deleteById(long id);

    Optional<Author> getById(long id);

    Optional<Author> getByName(String  name);

    List<Author> getAll();


}
