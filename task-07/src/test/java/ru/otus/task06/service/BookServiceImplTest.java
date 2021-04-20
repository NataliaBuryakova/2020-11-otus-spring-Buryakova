package ru.otus.task06.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.task06.domain.Author;
import ru.otus.task06.domain.Book;
import ru.otus.task06.domain.Genre;
import ru.otus.task06.repository.BookRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Service для работы с книгами должен")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class BookServiceImplTest {
    private static final long FIRST_BOOK_ID = 1L;
    private static final String FIRST_BOOK_NAME = "Незнайка в солнечном городе";
    private static final String SECOND_AUTHOR_NAME = "Н. Носов";
    private static final long SECOND_AUTHOR_ID = 2L;
    private static final long SECOND_GENRE_ID =  2L;
    private static final String SECOND_GENRE_KING = "Сказки";

    private static final long NEW_BOOK_ID = 5L;
    private static final String NEW_BOOK_NAME = "Идиот";
    private static final long NEW_AUTHOR_ID = 6L;
    private static final String NEW_AUTHOR_NAME = "Ф. Достоевский";
    private static final long NEW_GENRE_ID =  4L;
    private static final String NEW_GENRE_KING = "Роман";


    @Autowired
    private BookService bookService;
    @MockBean
    private BookRepository bookRepository;
    @BeforeEach
    void initBook(){

    }
    @DisplayName("создать нужную книгу и вернуть корректный ИД")
    @Test
    void shoudCreateCorrectBook() {
        var newBook = new Book(NEW_BOOK_NAME);
        newBook.setAuthor(new Author( NEW_AUTHOR_NAME));
        newBook.setGenre(new Genre(NEW_GENRE_KING ));
        var mockBook = newBook;
        mockBook.setId(NEW_BOOK_ID);
        when(bookRepository.save(any())).thenReturn(mockBook);
        assertThat(bookService.create(newBook)).isEqualTo(NEW_BOOK_ID);
    }

    @DisplayName("возвращать корректную книгу по ИД")
    @Test
    void shouldFindBookById() {
        Book initBook = new Book(FIRST_BOOK_ID,FIRST_BOOK_NAME);
        initBook.setAuthor(new Author(SECOND_AUTHOR_ID, SECOND_AUTHOR_NAME));
        initBook.setGenre(new Genre(SECOND_GENRE_ID,SECOND_GENRE_KING ));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(initBook));
        var actualBook = bookService.getById(FIRST_BOOK_ID);
        assertThat(actualBook)
                .isPresent()
                .hasValue(initBook);

    }


}