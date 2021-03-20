package ru.otus.task06.dao;

import ru.otus.task06.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    long insert(Author author);

    void update(Author author);

    void deleteById(long id);

    Optional<Author> findById(long id);

    Optional<Author> findByName(String  name);

    List<Author> findAll();

    Author save(Author author);


}
