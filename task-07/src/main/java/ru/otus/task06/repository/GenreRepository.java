package ru.otus.task06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.task06.domain.Genre;

import java.util.List;
import java.util.Optional;
@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {


    Optional<Genre> findByKind(String kind);



}
