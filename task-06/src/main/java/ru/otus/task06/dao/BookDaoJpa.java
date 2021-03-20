package ru.otus.task06.dao;

import ru.otus.task06.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class BookDaoJpa implements BookDao {
    @PersistenceContext
    private EntityManager em;
    @Override
    public long insert(Book book) {
        return 0;
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Book a " +
                "where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        return null;
    }
}
