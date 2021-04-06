package ru.otus.task06.dao;

import org.springframework.stereotype.Repository;
import ru.otus.task06.domain.Author;
import ru.otus.task06.domain.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
@Repository
public class BookDaoJpa implements BookDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public long insert(Book book) {
        return save(book).getId();
    }

    @Override
    public void update(Book book) {
        save(book);
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
        EntityGraph<?> entityGraph = em.getEntityGraph("author-genre-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.id=:id", Book.class);
        query.setParameter("id", id);
        query.setHint("javax.persistence.fetchgraph",entityGraph);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Book> findAll() {
        //выполняет 1 запрос
        EntityGraph<?> entityGraph = em.getEntityGraph("author-genre-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph",entityGraph);
        return query.getResultList();
        //выполняет 8 запросов
       /* return em.createQuery("select s from Book s", Book.class)
                .getResultList();*/
    }
    private Book save(Book book) {
        if(book.getId()==null){
            em.persist(book);
            em.flush();
            return book;
        }else {
            return em.merge(book);
        }

    }
}
