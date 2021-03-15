package ru.otus.task05.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.task05.dao.GenreDao;
import ru.otus.task05.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService{
    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public long create(String name) {
        return  genreDao.insert(new Genre(name));
    }

    @Override
    public void update(Genre genre) {
        genreDao.update(genre);
    }

    @Override
    public void deleteById(long id) {
        genreDao.deleteById(id);
    }

    @Override
    public Optional<Genre> getById(long id) {
        return genreDao.getById(id);
    }

    @Override
    public Optional<Genre> getByKind(String kind) {
        return genreDao.getByKind(kind);
    }
    @Override
    public Genre getOrCreateByKind(String kind) {
        return getByKind(kind).orElseGet(()->getById(create(kind)).orElseThrow(() -> new IllegalStateException(
                "Не удалось получить или создать жанр ")));
       // было без Optional
        /* Genre genre;
        try {
            genre = getByKind(kind);
        }catch (EmptyResultDataAccessException ex){
            long genreId = create(kind);
            genre = getById(genreId).get();
        }
        return genre;*/
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

}
