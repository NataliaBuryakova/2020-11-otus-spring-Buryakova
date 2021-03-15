package ru.otus.task05.controller;

import org.springframework.stereotype.Controller;
import ru.otus.task05.domain.Author;
import ru.otus.task05.domain.Book;
import ru.otus.task05.domain.Genre;
import ru.otus.task05.service.AuthorService;
import ru.otus.task05.service.BookService;
import ru.otus.task05.service.GenreService;
import ru.otus.task05.service.InOutService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class BookController {
    private final InOutService inOutService;
    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;
    public BookController(InOutService inOutService, BookService bookService, GenreService genreService, AuthorService authorService) {
        this.inOutService = inOutService;
        this.bookService = bookService;
        this.genreService = genreService;
        this.authorService = authorService;
    }
    //собираем информацию от пользователя
    public Book getNewBookInfo() {
        inOutService.println("Введите название новой книги");
        String title = inOutService.read();
        inOutService.println("Введите имя автора");
        String authorName = inOutService.read();
        var author = new Author(authorName);
        inOutService.println("Введите жанр");
        String genreName = inOutService.read();
        var genre = new Genre(genreName);
        return new Book(title, author, genre);
    }
    //собираем информацию от пользователя
    public Book getUpdateBookInfo(long id) {
        var ob = bookService.getById(id);
        ob.ifPresentOrElse(
                (book) ->
                {   inOutService.println(String.format("Найдена книга со следующими характеристиками:\n %s", getDescription(book)));
                    inOutService.println("Введите новое название книги");
                    String newTitle = inOutService.read();
                    inOutService.println("Введите новый жанр");
                    String newGenreKind = inOutService.read();
                    inOutService.println("Введите новое имя автора");
                    String newAuthorName = inOutService.read();
                    var genre = new Genre(newGenreKind); //or find
                    var author = new Author(newAuthorName); //or find
                    book.setTitle(newTitle);
                    book.setAuthor(author);
                    book.setGenre(genre);

                },
                () ->
                {
                    inOutService.println(
                        String.format("Информация о книге: \n%s"
                                ,"не найдена"));
                }
        );
        return ob.get();
    }
    //запускаем действие по созданию книги
    public void createBook(Book bookInfo){
        bookService.create(bookInfo);
        inOutService.println("Книга успешно создана");
    }
    //запускаем действие по обновлению книги
    public void updateBook(Book bookInfo){
        bookService.update(bookInfo);
        inOutService.println("Книга успешно обновлена");
    }
    public void deleteBookById(long id){
        bookService.deleteById(id);
        inOutService.println("Книга успешно удалена");
    }
    public void printBookInfoById(long id){
       bookService.getById(id).ifPresentOrElse(
                (book) ->
                {inOutService.println(
                        String.format("Информация о книге: \n%s"
                                ,getDescription(book)));
                },
                () ->
                {inOutService.println(
                        String.format("Информация о книге: \n%s"
                                ,"не найдена"));
                }
        );
        /*if (optionalBook.isPresent()) {
            bookInfo = getDescription(optionalBook.get());
        } else {
            bookInfo =  "не найдена";
        }
        inOutService.println(String.format("Информация о книге: \n%s",bookInfo));
*/
    }
    public void printListBook(){
        List<Book> list = bookService.getAll();
        inOutService.println(String.format("Список книг в библиотеке: \n%s", getListDescription(list)));
    }

    public void printErrorId(){
        inOutService.println("ID книги не задан");
    }
    private String getDescription(Book book){
        return String.format("- Идентификатор книги: %s \n  Название книги: %s \n  Автор книги: %s \n  Жанр книги: %s\n",
                                book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getKind());
    }
    private String getListDescription(List<Book> list){
        return list.stream()
                .map(this::getDescription).collect(Collectors.joining());
    }
}
