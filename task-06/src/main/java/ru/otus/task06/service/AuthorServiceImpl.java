package ru.otus.task06.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.task06.dao.AuthorDao;
import ru.otus.task06.domain.Author;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }
    @Transactional
    @Override
    public long create(String name) {
        return authorDao.insert(new Author(name));
    }
    @Transactional
    @Override
    public void update(Author author) {
        authorDao.update(author);
    }
    @Transactional
    @Override
    public void deleteById(long id) {
        authorDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Author> getById(long id) {
        return authorDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Author> getByName(String name) {
        return authorDao.findByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        return authorDao.findAll();
    }

    @Override
    public Author getOrCreateByName(String name) {
        return getByName(name).orElseGet(()->getById(create(name)).orElseThrow(() -> new IllegalStateException(
                "Не удалось получить или создать автора ")));
    }
}
