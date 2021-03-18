package ru.otus.task05.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.task05.dao.BookDao;
import ru.otus.task05.dao.GenreDao;
import ru.otus.task05.domain.Author;
import ru.otus.task05.domain.Book;
import ru.otus.task05.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final GenreService genreService;
    private final AuthorService authorService;


    public BookServiceImpl(BookDao bookDao, GenreDao genreDao, GenreService genreService, AuthorService authorService) {
        this.bookDao = bookDao;
        this.genreService = genreService;
        this.authorService = authorService;
    }

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
        return bookDao.insert(bookForCreate);
    }

    @Override
    public void update(Book bookForUpdate) {
        Genre genre = genreService.getOrCreateByKind(bookForUpdate.getGenre().getKind());
        Author author = authorService.getOrCreateByName(bookForUpdate.getAuthor().getName());
        bookForUpdate.setGenre(genre);
        bookForUpdate.setAuthor(author);
        bookDao.update(bookForUpdate);
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public Optional<Book> getById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }


}
