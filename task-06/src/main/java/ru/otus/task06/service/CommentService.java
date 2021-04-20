package ru.otus.task06.service;

import ru.otus.task06.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    long create(Comment commentForCreate);

    void update(Comment commentForUpdate);

    void deleteById(long id);

    Optional<Comment> getById(long id);

    List<Comment> getAll();

    List<Comment> getCommentListByBookId(long bookId);
}
