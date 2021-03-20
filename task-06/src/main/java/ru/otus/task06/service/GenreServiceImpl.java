package ru.otus.task06.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.task06.dao.GenreDao;
import ru.otus.task06.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService{
    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Transactional
    @Override
    public long create(String name) {
        return  genreDao.insert(new Genre(name));
    }

    @Transactional
    @Override
    public void update(Genre genre) {
        genreDao.update(genre);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        genreDao.deleteById(id);
    }

    @Override
    public Optional<Genre> getById(long id) {
        return genreDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Genre> getByKind(String kind) {
        return genreDao.findByKind(kind);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public Genre getOrCreateByKind(String kind) {
        return getByKind(kind).orElseGet(()->getById(create(kind)).orElseThrow(() -> new IllegalStateException(
                "Не удалось получить или создать жанр ")));
    }
}
