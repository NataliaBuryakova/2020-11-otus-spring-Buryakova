package ru.otus.task06.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.task06.domain.Author;
import ru.otus.task06.domain.Book;
import ru.otus.task06.domain.Genre;
import ru.otus.task06.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final AuthorService authorService;


    public BookServiceImpl(BookRepository bookRepository, GenreService genreService, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    @Transactional
    @Override
    public long create(Book bookForCreate) {
        //проверить есть ли введенный автор и жанр по названию:
        //  если нет - создать и обновить данные в книге.
        // если есть - обновить данные в книге
        // если есть  больше одного, вывести инфо о конфликтах
        Genre genre = genreService.getOrCreateByKind(bookForCreate.getGenre().getKind());
        Author author = authorService.getOrCreateByName(bookForCreate.getAuthor().getName());
        bookForCreate.setGenre(genre);
        bookForCreate.setAuthor(author);
        return bookRepository.save(bookForCreate).getId();
    }

    @Transactional
    @Override
    public void update(Book bookForUpdate) {
        Genre genre = genreService.getOrCreateByKind(bookForUpdate.getGenre().getKind());
        Author author = authorService.getOrCreateByName(bookForUpdate.getAuthor().getName());
        bookForUpdate.setGenre(genre);
        bookForUpdate.setAuthor(author);
        bookRepository.save(bookForUpdate);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> getById(long id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }


}
