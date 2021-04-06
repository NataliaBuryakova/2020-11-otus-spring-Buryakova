package ru.otus.task06.dao;

import org.springframework.stereotype.Repository;
import ru.otus.task06.domain.Author;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
@Repository
public class AuthorDaoJpa implements AuthorDao{
    @PersistenceContext
    private EntityManager em;



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
        Author author  = null;
        try {
            TypedQuery<Author> query = em.createQuery("select s " +
                            "from Author s " +
                            "where s.name = :name",
                    Author.class);
            query.setParameter("name", name);
            author = query.getSingleResult();
        }catch (NoResultException ignored){
            //TODO красивая обработка исключения + использование Optional
        }
        return Optional.ofNullable(author);
    }

    @Override
    public List<Author> findAll() {
        return em.createQuery("select s from Author s", Author.class)
                .getResultList();
    }

    private Author save(Author author) {
        if(author.getId()==null){
            em.persist(author);
            em.flush();
            return author;
        }else {
            return em.merge(author);
        }

    }
}
