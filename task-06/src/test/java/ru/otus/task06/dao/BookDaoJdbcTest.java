package ru.otus.task06.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.task06.domain.Author;
import ru.otus.task06.domain.Book;
import ru.otus.task06.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO для работы с книгами должен")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {
    private static final String FIELD_NAME = "title";
    private static final int EXPECTED_NUMBER_OF_BOOKS = 4;

    private static final long BOOK_ID_FOR_DELETE = 2L;
    private static final long FIRST_BOOK_ID = 1L;
    private static final String FIRST_BOOK_NAME = "Незнайка в солнечном городе";
    private static final String SECOND_AUTHOR_NAME = "Н. Носов";
    private static final long SECOND_AUTHOR_ID = 2L;
    private static final long SECOND_GENRE_ID =  2L;
    private static final String SECOND_GENRE_KING = "Сказки";

    private static final String UPDATE_BOOK_NAME = "Незнайка на луне";
    private static final long NEW_BOOK_ID = 5L;
    private static final String NEW_BOOK_NAME = "Идиот";
    private static final long NEW_AUTHOR_ID = 6L;
    private static final String NEW_AUTHOR_NAME = "Ф. Достоевский";
    private static final long NEW_GENRE_ID =  4L;
    private static final String NEW_GENRE_KING = "Роман";

    @Autowired
    private BookDao bookDao;

    @DisplayName("создать нужную книгу и загрузить информацию о ней")
    @Test
    void shouldCreateAndLoadCorrectBook() {
        var newBook = new Book(NEW_BOOK_NAME);
        newBook.setAuthor(new Author(NEW_AUTHOR_ID, NEW_AUTHOR_NAME));
        newBook.setGenre(new Genre(NEW_GENRE_ID,NEW_GENRE_KING ));
        bookDao.insert(newBook);
        var findBook = bookDao.findById(NEW_BOOK_ID);
        assertThat(findBook)
                .isPresent().get().hasFieldOrPropertyWithValue(FIELD_NAME,NEW_BOOK_NAME);


    }
    @DisplayName(" обновить заголовок книги в БД и загрузить информацию о ней")
    @Test
    void shouldUpdateAndLoadCorrectBook() {
    Optional<Book> bookO = bookDao.findById(FIRST_BOOK_ID);
        bookO.ifPresent(value -> {
        value.setTitle(UPDATE_BOOK_NAME);
        bookDao.update(value);
    });
    var findBook = bookDao.findById(FIRST_BOOK_ID);
    assertThat(findBook)
            .isPresent().get().hasFieldOrPropertyWithValue(FIELD_NAME,UPDATE_BOOK_NAME);

    }

    @DisplayName("удалять книгу из БД по id")
    @Test
    void shouldDeleteBookFromDbById() {
        bookDao.deleteById(BOOK_ID_FOR_DELETE);
        var findBook = bookDao.findById(BOOK_ID_FOR_DELETE);
        assertThat(findBook).isEmpty();
    }
    @DisplayName(" загружать информацию о нужной книге по ИД")
    @Test
    void shouldFindExpectedBookById() {
        Book initBook = new Book(FIRST_BOOK_ID,FIRST_BOOK_NAME);
        initBook.setAuthor(new Author(SECOND_AUTHOR_ID, SECOND_AUTHOR_NAME));
        initBook.setGenre(new Genre(SECOND_GENRE_ID,SECOND_GENRE_KING ));
        var actualBook = bookDao.findById(FIRST_BOOK_ID);
        assertThat(actualBook)
                .isPresent()
                .hasValue(initBook);

    }


    @DisplayName("возвращать список всех жанров")
    @Test
    void shouldReturnCorrectAuthorList() {
        List<Book> books = bookDao.getAll();
        assertThat(books).hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(s -> !s.getTitle().equals(""))
                .allMatch(s -> s.getAuthor()!=null&&!s.getAuthor().getName().equals(""))
                .allMatch(s -> s.getGenre()!=null&&!s.getGenre().getKind().equals(""))
        ;

    }
}