package ru.otus.task05.dao;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.task05.domain.Author;
import ru.otus.task05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class GenreDaoJdbc implements GenreDao{
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public long insert(Genre genre) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("kind", genre.getKind());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into genre (kind) values ( :kind)",
                mapSqlParameterSource, keyHolder);
        return (long) keyHolder.getKey();
    }

    @Override
    public void update(Genre genre) {
        namedParameterJdbcOperations.update("update genre set 'kind' = :kind where id = :id",
                Map.of("id", genre.getId(), "kind", genre.getKind()));
    }

    @Override
    public void deleteById(long id) {
        namedParameterJdbcOperations.update("delete from genre where id = :id",
                Map.of("id", id));
    }

    @Override
    public Optional<Genre> getById(long id) {
        Genre genre = null;
        try {
            genre = namedParameterJdbcOperations.queryForObject("select genre.id, genre.kind from genre where id = :id",
                    Map.of("id", id),new GenreMapper());
        }catch (EmptyResultDataAccessException ignored){
            //TODO красивая обработка исключения + использование Optional
        }
        return Optional.ofNullable(genre);
    }

    @Override
    public Optional<Genre> getByKind(String kind) throws EmptyResultDataAccessException {
        Genre genre = null;
        try {
            genre = namedParameterJdbcOperations.queryForObject("select genre.id, genre.kind from genre where kind = :kind",
                    Map.of("kind", kind),new GenreMapper());
        }catch (EmptyResultDataAccessException ignored){
            //TODO красивая обработка исключения + использование Optional
        }
        return Optional.ofNullable(genre);
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.query("select genre.id, genre.kind from genre ",
                new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String kind = resultSet.getString("kind");
            return new Genre(id, kind);
        }
    }
}
