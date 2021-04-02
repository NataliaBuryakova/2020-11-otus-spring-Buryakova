package ru.otus.task06.dao;

import ru.otus.task06.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    long insert(Genre genre);

    void update(Genre genre);

    void deleteById(long id);

    Optional<Genre> findById(long id);

    Optional<Genre> findByKind(String kind);

    List<Genre> findAll();

    Genre save(Genre genre);


}
