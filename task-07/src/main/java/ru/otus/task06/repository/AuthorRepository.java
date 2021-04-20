package ru.otus.task06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.task06.domain.Author;

import java.util.Optional;
@Repository
public interface AuthorRepository  extends JpaRepository<Author,Long> {

    Optional<Author> findByName(String name);




}
