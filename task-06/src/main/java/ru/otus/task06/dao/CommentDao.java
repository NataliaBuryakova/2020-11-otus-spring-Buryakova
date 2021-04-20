package ru.otus.task06.dao;

import ru.otus.task06.domain.Book;
import ru.otus.task06.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    long insert(Comment comment);

    void update(Comment comment);

    void deleteById(long id);

    Optional<Comment> findById(long id);

    List<Comment> findAll();

    List<Comment> findByBookId(long bookId);

}
