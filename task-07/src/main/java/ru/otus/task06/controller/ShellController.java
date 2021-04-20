package ru.otus.task06.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.task06.domain.Book;
import ru.otus.task06.domain.Comment;

import java.util.Optional;


@ShellComponent
public class ShellController {
    private final BookController bookController;
    private final AuthorController authorController;
    private final GenreController genreController;
    private final CommentController commentController;
    public ShellController(BookController bookController, AuthorController authorController, GenreController genreController, CommentController commentController) {
        this.bookController = bookController;
        this.authorController = authorController;
        this.genreController = genreController;
        this.commentController = commentController;
    }

    //Books operations
    @ShellMethod(value = "create Book", key = {"cb", "createBook"})
    public void createBook() {
        bookController.getNewBookInfo().ifPresent(
                bookController::createBook
        );

    }
    @ShellMethod(value = "update Book", key = {"ub", "updateBook"})
    public void updateBook(@ShellOption long id) {
        bookController.getUpdateBookInfo(id).ifPresent(
                bookController::updateBook
        );

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

    //Authors operations
    @ShellMethod(value = "get Author List", key = {"gal", "getAuthorList"})
    public void getAuthorList() {
        authorController.printList();
    }
    @ShellMethod(value = "delete Author", key = {"da", "deleteAuthor"})
    public void deleteAuthor(@ShellOption long id) {
        authorController.deleteAuthorById(id);
    }
    @ShellMethod(value = "find Author by Id", key = {"fa", "findAuthorId"})
    public void findAuthorId(@ShellOption long id) {
        authorController.printInfoById(id);
    }
    @ShellMethod(value = "create Author", key = {"ca", "createAuthor"})
    public void createAuthor(@ShellOption String name) {
        authorController.createAuthor(name);
    }

    //Genre operations
    @ShellMethod(value = "get Genre List", key = {"ggl", "getGenreList"})
    public void getGenreList() {
        genreController.printList();
    }

    //Comment operations
    @ShellMethod(value = "get Comment List by Book Id", key = {"gclb", "getCommentListByBook"})
    public void getCommentListByBookId(long bookId) {
        bookController.printBookInfoById(bookId);
        commentController.printCommentListByBookId(bookId);
    }
    @ShellMethod(value = "get Comment List", key = {"gcl", "getCommentList"})
    public void getCommentList() {
        commentController.printCommentList();
    }
    @ShellMethod(value = "delete Comment", key = {"dс", "deleteComment"})
    public void deleteComment(@ShellOption long id) {
        authorController.deleteAuthorById(id);
    }
    @ShellMethod(value = "find Comment by Id", key = {"fc", "findCommentId"})
    public void findCommentId(@ShellOption long id) {
        commentController.printInfoById(id);
    }
    @ShellMethod(value = "create Comment", key = {"cc", "createComment"})
        public void createComment(@ShellOption long bookId) {
            commentController.getNewCommentInfo(bookId).ifPresent(
                    commentController::createComment
            );
    }
    @ShellMethod(value = "update Comment", key = {"uc", "updateComment"})
    public void updateComment(@ShellOption long id) {
        commentController.getUpdateCommentInfo(id).ifPresent(
                commentController::updateComment
        );
    }





}
