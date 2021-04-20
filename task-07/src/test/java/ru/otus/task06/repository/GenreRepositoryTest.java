package ru.otus.task06.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Репозиторий на основе SpringData для работы с жанрами ")
@DataJpaTest
class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;

    private static final String GENRE_KING = "Сказки";
    @DisplayName(" должен загружать информацию о нужном жанре по его названию")
    @Test
    void shouldFindExpectedGenreByKind() {
        val optionalActualGenre = genreRepository.findByKind(GENRE_KING);
        assertThat(optionalActualGenre).isPresent().get()
                .hasFieldOrPropertyWithValue("kind",GENRE_KING);
    }
}