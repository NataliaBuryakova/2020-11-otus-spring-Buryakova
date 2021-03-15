package ru.otus.task05.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.task05.dao.AuthorDao;
import ru.otus.task05.domain.Author;
import ru.otus.task05.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public long create(String name) {
        return authorDao.insert(new Author(name));
    }

    @Override
    public void update(Author author) {
        authorDao.update(author);
    }

    @Override
    public void deleteById(long id) {
        authorDao.deleteById(id);
    }

    @Override
    public Optional<Author> getById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public Optional<Author> getByName(String name) {
        return authorDao.getByName(name);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public Author getOrCreateByName(String name) {
        return getByName(name).orElseGet(()->getById(create(name)).orElseThrow(() -> new IllegalStateException(
                "Не удалось получить или создать автора ")));
    }
}
