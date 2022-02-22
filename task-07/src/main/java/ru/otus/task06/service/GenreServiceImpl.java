package ru.otus.task06.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.task06.domain.Genre;
import ru.otus.task06.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService{
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional
    @Override
    public long create(String name) {
        return  genreRepository.save(new Genre(name)).getId();
    }

    @Transactional
    @Override
    public void update(Genre genre) {
        genreRepository.save(genre);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public Optional<Genre> getById(long id) {
        return genreRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Genre> getByKind(String kind) {
        return genreRepository.findByKind(kind);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getOrCreateByKind(String kind) {
        return getByKind(kind).orElseGet(()->getById(create(kind)).orElseThrow(() -> new IllegalStateException(
                "Не удалось получить или создать жанр ")));
    }
}
