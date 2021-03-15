package ru.otus.task05.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.task05.domain.Author;
import ru.otus.task05.domain.Book;
import ru.otus.task05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }
    @Override
    public long insert(Book book) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValues(  Map.of("title", book.getTitle(), "authorId", book.getAuthor().getId(), "genreid", book.getGenre().getId()));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into book (title,authorId,genreid) values(:title,:authorId,:genreid)",
                mapSqlParameterSource,keyHolder);
        return (long) keyHolder.getKey();
    }

    @Override
    public void update(Book book) {
        namedParameterJdbcOperations.update("update book set title = :title, authorId = :authorId,genreid = :genreid where id = :id",
                Map.of("title", book.getTitle(), "authorId", book.getAuthor().getId(), "genreid", book.getGenre().getId(), "id", book.getId()));
    }

    @Override
    public void deleteById(long id) {
        namedParameterJdbcOperations.update("delete from book where id = :id",
                Map.of("id", id));
    }

    @Override
    public Optional<Book> getById(long id)  {
        Book book = null;
        try {
             book = namedParameterJdbcOperations.queryForObject("select b.id, b.title, b.genreId, b.authorId, a.name authorName, g.kind genreKind " +
                            "from (book b left join author a on b.authorId = a.id) " +
                            "left join genre g on b.genreId = g.id where b.id = :id",
                    Map.of("id", id),
                    new BookMapper());

        }
        catch (EmptyResultDataAccessException ignored){
            //TODO красивую обработку исключения + использование Optional
        }
        return Optional.ofNullable(book);

    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select b.id, b.title, b.genreId, b.authorId, a.name authorName, g.kind genreKind " +
                        "from (book b left join author a on b.authorId = a.id) " +
                        "left join genre g on b.genreId = g.id",
                new BookMapper());
    }
    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            Book book = new Book(id, title);
            book.setAuthor(new Author(resultSet.getLong("authorId"), resultSet.getString("authorName")));
            book.setGenre(new Genre(resultSet.getLong("genreId"), resultSet.getString("genreKind")));
            return book;
        }
    }
}
