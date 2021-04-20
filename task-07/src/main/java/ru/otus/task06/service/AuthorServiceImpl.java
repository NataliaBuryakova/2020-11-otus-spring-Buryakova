package ru.otus.task06.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.task06.domain.Author;
import ru.otus.task06.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @Transactional
    @Override
    public long create(String name) {
        return authorRepository.save(new Author(name)).getId();
    }
    @Transactional
    @Override
    public void update(Author author) {
        authorRepository.save(author);
    }
    @Transactional
    @Override
    public void deleteById(long id) {
        authorRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Author> getById(long id) {
        return authorRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Author> getByName(String name) {
        return authorRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getOrCreateByName(String name) {
        return getByName(name).orElseGet(()->getById(create(name)).orElseThrow(() -> new IllegalStateException(
                "Не удалось получить или создать автора ")));
    }
}
