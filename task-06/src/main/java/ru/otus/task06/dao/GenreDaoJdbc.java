package ru.otus.task06.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.task06.domain.Genre;

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
        return keyHolder.getKey().longValue();
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
    public Optional<Genre> findById(long id) {
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
    public Optional<Genre> findByKind(String kind) throws EmptyResultDataAccessException {
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
    public List<Genre> findAll() {
        return namedParameterJdbcOperations.query("select genre.id, genre.kind from genre ",
                new GenreMapper());
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() != null) {
            update(genre);
        } else {
            long id = insert(genre);
            genre.setId(id);
        }
        return genre;
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
