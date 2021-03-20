package ru.otus.task06.dao;

import org.springframework.stereotype.Repository;
import ru.otus.task06.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
@Repository
public class AuthorDaoJpa implements AuthorDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Author save(Author author) {
            return em.merge(author);
    }
    @Override
    public long insert(Author author) {
        return save(author).getId();
    }

    @Override
    public void update(Author author) {
        save(author);
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Author a " +
                "where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class,id));
    }

    @Override
    public Optional<Author> findByName(String name) {
        TypedQuery<Author> query = em.createQuery("select s " +
                        "from Author s " +
                        "where s.name = :name",
                Author.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Author> findAll() {
        return null;
    }
}
