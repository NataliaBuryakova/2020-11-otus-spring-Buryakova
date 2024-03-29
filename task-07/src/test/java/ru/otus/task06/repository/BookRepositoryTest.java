package ru.otus.task06.repository;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.task06.domain.Author;
import ru.otus.task06.domain.Book;
import ru.otus.task06.domain.Genre;
import ru.otus.task06.repository.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе SpringData для работы с книгами ")
@DataJpaTest

class BookRepositoryTest {
    private static final int EXPECTED_NUMBER_OF_QUERY_GRAF = 1;
    private static final int EXPECTED_NUMBER_OF_BOOKS = 4;
    private static final long FIRST_BOOK_ID = 1L;
    private static final String UPDATE_BOOK_NAME = "Незнайка на луне";
    private static final String NEW_BOOK_NAME = "Идиот";
    private static final long NEW_AUTHOR_ID = 6L;
    private static final String NEW_AUTHOR_NAME = "Ф. Достоевский";
    private static final long NEW_GENRE_ID =  4L;
    private static final String NEW_GENRE_KING = "Роман";

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TestEntityManager em;


    @DisplayName(" должен загружать информацию о нужной книге по его id")
    @Test
    void shouldFindExpectedBookById() {
        val optionalActualBook = bookRepository.findById(FIRST_BOOK_ID);
        val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(optionalActualBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("должен загружать список всех книг с информацией о них в одном запросе")
    @Test
    void shouldReturnCorrectBookListWithAllInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        sessionFactory.getStatistics().clear();
        val books = bookRepository.findAll();

        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(b -> !b.getTitle().equals(""))
                .allMatch(b -> b.getAuthor()!=null&&!b.getAuthor().getName().equals(""))
                .allMatch(b -> b.getGenre()!=null&&!b.getGenre().getKind().equals(""))

        ;
        System.out.println(books.toString());
        System.out.println(sessionFactory.getStatistics().getPrepareStatementCount());
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_NUMBER_OF_QUERY_GRAF);
    }

    @DisplayName(" должен корректно сохранять всю информацию о книге")
    @Test
    void shouldSaveAllBookInfo() {
        var newBook = new Book(NEW_BOOK_NAME);
        newBook.setAuthor(new Author( NEW_AUTHOR_NAME));
        newBook.setGenre(new Genre(NEW_GENRE_KING ));
        long id = bookRepository.save(newBook).getId();
        assertThat(id).isGreaterThan(0);

        val actualBook = em.find(Book.class, id);
        assertThat(actualBook).isNotNull()
                .matches(s -> !s.getTitle().equals(""))
                .matches(s -> s.getAuthor()!=null&&!s.getAuthor().getName().equals(""))
                .matches(s -> s.getGenre()!=null&&!s.getGenre().getKind().equals(""));

    }



    @DisplayName(" должен изменять заголовок заданной книги по ее id")
    @Test
    void shouldUpdateBookNameById() {
        val editBook = em.find(Book.class, FIRST_BOOK_ID);
        String oldName = editBook.getTitle();
        em.detach(editBook);
        editBook.setTitle(UPDATE_BOOK_NAME);
        bookRepository.save(editBook);
        val updatedBook = em.find(Book.class, FIRST_BOOK_ID);

        assertThat(updatedBook.getTitle()).isNotEqualTo(oldName).isEqualTo(UPDATE_BOOK_NAME);
    }

    @DisplayName(" должен удалять заданную книгу  по ее id")
    @Test
    void shouldDeleteBookById() {
        val toDeleteBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(toDeleteBook).isNotNull();
        em.detach(toDeleteBook);

        bookRepository.deleteById(FIRST_BOOK_ID);
        val deletedBook = em.find(Book.class, FIRST_BOOK_ID);

        assertThat(deletedBook).isNull();
    }
}