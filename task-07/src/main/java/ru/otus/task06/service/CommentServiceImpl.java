package ru.otus.task06.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.task06.domain.Comment;
import ru.otus.task06.repository.CommentRepository;

import java.util.List;
import java.util.Optional;
@Service
public class CommentServiceImpl  implements CommentService{
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    @Transactional
    @Override
    public long create(Comment commentForCreate) {
        return commentRepository.save(commentForCreate).getId();
    }
    @Transactional
    @Override
    public void update(Comment commentForUpdate) {
        commentRepository.save(commentForUpdate);
    }
    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> getById(long id) {
        return commentRepository.findById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public List<Comment> getCommentListByBookId(long bookId) {
        return commentRepository.findByBook_Id(bookId);
    }
}
