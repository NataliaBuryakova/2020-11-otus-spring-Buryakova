package ru.otus.task06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.task06.domain.Comment;

import java.util.List;
import java.util.Optional;
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

   List<Comment> findByBook_Id(long bookId);

}
