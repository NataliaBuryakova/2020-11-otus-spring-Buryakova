package ru.otus.task06.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.task06.dao.CommentDao;
import ru.otus.task06.domain.Comment;

import java.util.List;
import java.util.Optional;
@Service
public class CommentServiceImpl  implements CommentService{
    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
    @Transactional
    @Override
    public long create(Comment commentForCreate) {
        return commentDao.insert(commentForCreate);
    }
    @Transactional
    @Override
    public void update(Comment commentForUpdate) {
        commentDao.update(commentForUpdate);
    }
    @Transactional
    @Override
    public void deleteById(long id) {
        commentDao.deleteById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> getById(long id) {
        return commentDao.findById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Comment> getAll() {
        return commentDao.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public List<Comment> getCommentListByBookId(long bookId) {
        return commentDao.findByBookId(bookId);
    }
}
