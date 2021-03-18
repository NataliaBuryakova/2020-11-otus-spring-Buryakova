package ru.otus.task05.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.task05.domain.Book;
import ru.otus.task05.service.AuthorService;
import ru.otus.task05.service.GenreService;


@ShellComponent
public class ShellController {
    private final BookController bookController;
    private final AuthorController authorController;
    private final GenreController genreController;
    public ShellController(BookController bookController, AuthorController authorController, GenreController genreController) {
        this.bookController = bookController;
        this.authorController = authorController;
        this.genreController = genreController;
    }

    @ShellMethod(value = "create Book", key = {"cb", "createBook"})
    public void createBook() {
        Book bookInfo = bookController.getNewBookInfo();
        bookController.createBook(bookInfo);
    }
    @ShellMethod(value = "update Book", key = {"ub", "updateBook"})
    public void updateBook(@ShellOption long id) {
        Book bookInfo = bookController.getUpdateBookInfo(id);
        bookController.updateBook(bookInfo);

    }
    @ShellMethod(value = "delete Book", key = {"db", "deleteBook"})
    public void deleteBook(@ShellOption long id) {
        bookController.deleteBookById(id);
    }
    @ShellMethod(value = "find Book by Id", key = {"fb", "findBookId"})
    public void findBookById(@ShellOption long id) {
        bookController.printBookInfoById(id);
    }
    @ShellMethod(value = "get Book List", key = {"gbl", "getBookList"})
    public void getBookList() {
        bookController.printListBook();
    }
    @ShellMethod(value = "get Author List", key = {"gal", "getAuthorList"})
    public void getAuthorList() {
        authorController.printList();
    }
    @ShellMethod(value = "delete Author", key = {"da", "deleteAuthor"})
    public void deleteAuthor(@ShellOption long id) {
        authorController.deleteAuthorById(id);
    }

    @ShellMethod(value = "get Genre List", key = {"ggl", "getGenreList"})
    public void getGenreList() {
        genreController.printList();
    }







}
