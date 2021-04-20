package ru.otus.task06.controller;

import org.springframework.stereotype.Service;
import ru.otus.task06.domain.Book;
import ru.otus.task06.domain.Comment;
import ru.otus.task06.service.BookService;
import ru.otus.task06.service.CommentService;
import ru.otus.task06.service.InOutService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentController {
    private final InOutService inOutService;
    private final CommentService commentService;
    private final BookService bookService;

    public CommentController(InOutService inOutService, CommentService commentService, BookService bookService) {
        this.inOutService = inOutService;
        this.commentService = commentService;
        this.bookService = bookService;
    }

    //запускаем действие по созданию комментария
    public void createComment(Comment commentInfo){
        commentService.create(commentInfo);
        inOutService.println("Комментарий к книге успешно создан");
    }
    //запускаем действие по обновлению комментария
    public void updateComment(Comment commentInfo){
        commentService.update(commentInfo);
        inOutService.println("Комментарий к книге успешно обновлен");
    }
    public void deleteCommentById(long id){
        commentService.deleteById(id);
        inOutService.println("Комментарий успешно удален");
    }
    public Optional<Comment> getNewCommentInfo(long bookId) {
        Comment comment = null;
        Optional<Book> bookOptional = bookService.getById(bookId);
        if (bookOptional.isPresent()){
            Book book = bookOptional.get();
            inOutService.println(String.format("Введите новый комментарий для книги %s",book.getTitle()));
            String title = inOutService.read();
            comment = new Comment(title);
            comment.setBook(book);
        }else {
            inOutService.println(
                    String.format("Информация о книге c ID :%s \n%s"
                            ,bookId,"не найдена"));
        }
        return Optional.ofNullable(comment);


    }
    public Optional<Comment> getUpdateCommentInfo(long id) {
        Optional<Comment> optionalComment = commentService.getById(id);
        optionalComment.ifPresentOrElse(
                (comment) ->
                {
                    inOutService.println(String.format("Введите новый текст комментария для книги %s", comment.getBook().getTitle()));
                    String newText = inOutService.read();
                    comment.setText(newText);

                },
                () ->
                {
                    inOutService.println(
                            String.format("Комментарий с ID : %s \n%s"
                                    ,id, "не найден"));
                }
        );
        return optionalComment;
    }
    public void printCommentListByBookId(long bookId){
        String info = "Комментарии к книге:";
        List<Comment> comments = commentService.getCommentListByBookId(bookId);
        comments.stream().findAny().ifPresentOrElse(
                (comment) ->
                {inOutService.println(
                        String.format(info+" \n%s"
                                ,getCommentListDescription(comments)));
                },
                () ->
                {inOutService.println(
                        String.format(info+" \n%s"
                                ,"не найдены"));
                }
        );
    }

    public void printCommentList(){
        List<Comment> comments = commentService.getAll();
        inOutService.println(String.format("Список комментариев: \n%s", getCommentListDescription(comments)));
    }
    public void printInfoById(long id){
        String info = "Комментарий с ID: ";
        commentService.getById(id).ifPresentOrElse(
                (comment) ->
                {inOutService.println(
                        String.format(info + " %s \n%s"
                                ,id,getDescription(comment)));
                },
                () ->
                {inOutService.println(
                        String.format(info + " %s \n%s"
                                ,id,"не найден"));
                }
        );
    }
    private String getDescription(Comment comment){
        return String.format("- Идентификатор комментария: %s \n  Текст Комментария: %s \n",
                comment.getId(), comment.getText());
    }
    private String getCommentListDescription(List<Comment> list){
        return list.stream()
                .map(this::getDescription).collect(Collectors.joining());
    }
}
