package ru.otus.task06.dao;

import org.springframework.stereotype.Repository;
import ru.otus.task06.domain.Author;
import ru.otus.task06.domain.Comment;
import ru.otus.task06.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
@Repository
public class GenreDaoJpa implements GenreDao {
    @PersistenceContext
    private EntityManager em;


    @Override
    public long insert(Genre genre) {
        return save(genre).getId();
    }

    @Override
    public void update(Genre genre) {
        save(genre);
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Genre g " +
                "where g.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class,id));
    }

    @Override
    public Optional<Genre> findByKind(String kind) {
        TypedQuery<Genre> query = em.createQuery("select s " +
                        "from Genre s " +
                        "where s.kind = :kind",
                Genre.class);
        query.setParameter("kind", kind);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Genre> findAll() {
        return em.createQuery("select s from Genre s", Genre.class)
                .getResultList();
    }
    private Genre save(Genre genre) {
        if(genre.getId()==null){
            em.persist(genre);
            em.flush();
            return genre;
        }else {
            return em.merge(genre);
        }

    }
}
