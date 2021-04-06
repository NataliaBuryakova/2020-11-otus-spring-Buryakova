package ru.otus.task06.dao;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.task06.domain.Book;
import ru.otus.task06.domain.Comment;

import static org.assertj.core.api.Assertions.assertThat;
@DisplayName("Репозиторий на основе Jpa для работы с комментариями ")
@DataJpaTest
@Import(CommentDaoJpa.class)
class CommentDaoJpaTest {
    private static final int EXPECTED_NUMBER_OF_ALL_COMMENTS = 5;
    private static final String NEW_COMMENT_TEXT = "comment_3To_book_1";
    private static final long FIRST_COMMENT_ID = 1L;
    private static final long FIRST_BOOK_ID = 1L;
    private static final int EXPECTED_NUMBER_OF_COMMENTS = 2;
    private static final int EXPECTED_NUMBER_OF_QUERY_LAZY = 1;
    private static final int EXPECTED_NUMBER_OF_QUERY = 4;
    @Autowired
    private CommentDaoJpa commentDaoJpa;
    @Autowired
    private TestEntityManager em;
    @DisplayName(" должен загружать информацию о комментарии по его id")
    @Test
    void shouldFindExpectedCommentById() {
        val optionalActualComment = commentDaoJpa.findById(FIRST_COMMENT_ID);
        val expectedComment = em.find(Comment.class, FIRST_COMMENT_ID);
        assertThat(optionalActualComment).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("должен загружать список всех комментариев по книге")
    @Test
    void shouldReturnCorrectCommentsByBookId() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        val comments = commentDaoJpa.findByBookId(FIRST_BOOK_ID);

        assertThat(comments).isNotNull().hasSize(EXPECTED_NUMBER_OF_COMMENTS)
                .allMatch(c -> !c.getText().equals(""))
                .allMatch(c -> c.getBook()!=null&&!c.getBook().getTitle().equals(""))
        ;
        System.out.println(comments.toString());
        System.out.println(sessionFactory.getStatistics().getPrepareStatementCount());
    }

    @DisplayName(" должен корректно сохранять  информацию о комментариях")
    @Test
    void shouldSaveCommentInfo() {
        var newComment = new Comment(NEW_COMMENT_TEXT);
        val book = em.find(Book.class, FIRST_BOOK_ID);
        newComment.setBook(book);
        long id = commentDaoJpa.insert(newComment);
        assertThat(id).isGreaterThan(0);

        val actualComment = em.find(Comment.class, id);
        assertThat(actualComment).isNotNull()
                .matches(c -> !c.getText().equals(""))
                .matches(c -> c.getBook()!=null&&!c.getBook().getTitle().equals(""))
        ;


    }



    @DisplayName(" должен изменять комментарий заданной книги по ее id")
    @Test
    void shouldUpdateCommentTextByBookId() {
        val editFirstComment = commentDaoJpa.findByBookId(FIRST_BOOK_ID).stream().findFirst().get();
        String oldText = editFirstComment.getText();
        em.detach(editFirstComment);
        editFirstComment.setText(NEW_COMMENT_TEXT);
        commentDaoJpa.update(editFirstComment);
        val updatedComment = em.find(Comment.class, editFirstComment.getId());

        assertThat(updatedComment.getText()).isNotEqualTo(oldText).isEqualTo(NEW_COMMENT_TEXT);
    }

    @DisplayName(" должен удалять заданный комметнарий  по его id")
    @Test
    void shouldDeletecommentById() {
        val toDeleteComment = em.find(Comment.class,FIRST_COMMENT_ID );
        assertThat(toDeleteComment).isNotNull();
        em.detach(toDeleteComment);

        commentDaoJpa.deleteById(FIRST_COMMENT_ID);
        val deletedComment = em.find(Comment.class, FIRST_COMMENT_ID);

        assertThat(deletedComment).isNull();
    }

    @DisplayName("должен загружать список всех комментариев без информации о книгах")
    @Test
    void shouldReturnCorrectCommentListWithOutBookInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        val comments = commentDaoJpa.findAll();

        assertThat(comments).isNotNull().hasSize(EXPECTED_NUMBER_OF_ALL_COMMENTS)
                .allMatch(b -> !b.getText().equals(""))
                //.allMatch(b -> b.getBook()!=null&&!b.getBook().getTitle().equals(""))

        ;
       // System.out.println(comments.toString());
        System.out.println(sessionFactory.getStatistics().getPrepareStatementCount());
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_NUMBER_OF_QUERY_LAZY);
    }
    @DisplayName("должен загружать список всех комментариев c информацией о книгах")
    @Test
    void shouldReturnCorrectCommentListWithBookInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        val comments = commentDaoJpa.findAll();

        assertThat(comments).isNotNull().hasSize(EXPECTED_NUMBER_OF_ALL_COMMENTS)
                .allMatch(b -> !b.getText().equals(""))
                .allMatch(b -> b.getBook()!=null&&!b.getBook().getTitle().equals(""))

        ;
        // System.out.println(comments.toString());
        System.out.println(sessionFactory.getStatistics().getPrepareStatementCount());
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_NUMBER_OF_QUERY);
    }
}