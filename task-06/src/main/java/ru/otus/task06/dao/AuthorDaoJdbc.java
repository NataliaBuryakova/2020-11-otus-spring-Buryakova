package ru.otus.task06.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.task06.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public long insert(Author author) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue( "name", author.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into author (name) values ( :name)",
                mapSqlParameterSource,keyHolder);
        return keyHolder.getKey().longValue();

    }



    @Override
    public void update(Author author) {
        namedParameterJdbcOperations.update("update author set 'name' = :name where id = :id",
                Map.of("id", author.getId(), "name", author.getName()));
    }

    @Override
    public void deleteById(long id) {
        namedParameterJdbcOperations.update("delete from author where id = :id",
                Map.of("id", id));
    }

    @Override
    public Optional<Author> findById(long id) {
        Author author  = null;
        try {
            author = namedParameterJdbcOperations.queryForObject("select author.id, author.name from author where id = :id",
                    Map.of("id", id),new AuthorMapper());
        }catch (EmptyResultDataAccessException ignored){
            //TODO красивая обработка исключения + использование Optional
        }
        return Optional.ofNullable(author);
    }
    @Override
    public Optional<Author> findByName(String  name) {
        Author author  = null;
        try {
            author =  namedParameterJdbcOperations.queryForObject("select author.id, author.name from author where name = :name",
                Map.of("name", name),new AuthorMapper());
        }catch (EmptyResultDataAccessException ignored){
            //TODO красивая обработка исключения + использование Optional
        }
        return Optional.ofNullable(author);
    }

    @Override
    public List<Author> findAll() {
        return namedParameterJdbcOperations.query("select author.id, author.name from author ",
                new AuthorMapper());
    }

    @Override
    public Author save(Author author) {
        return null;
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}
