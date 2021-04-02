package ru.otus.task06.dao;

import org.springframework.stereotype.Repository;
import ru.otus.task06.domain.Book;
import ru.otus.task06.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
@Repository
public class CommentDaoJpa implements CommentDao {
    @PersistenceContext
    private EntityManager em;
    @Override
    public Comment save(Comment comment) {
        return em.merge(comment);
    }

    @Override
    public long insert(Comment comment) {
        return save(comment).getId();
    }

    @Override
    public void update(Comment comment) {
        save(comment);
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Comment c " +
                "where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class,id));
    }

    @Override
    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }

    @Override
    public List<Comment> findByBookId(long bookId) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book.id=:id", Comment.class);
        query.setParameter("id", bookId);
        return query.getResultList();
    }
}
