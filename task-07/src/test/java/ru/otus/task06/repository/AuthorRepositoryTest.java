package ru.otus.task06.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе SpringData для работы с авторами ")
@DataJpaTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    private static final String AUTHOR_NAME = "Н. Носов";
    @DisplayName(" должен загружать информацию о нужном авторе по его имени")
    @Test
    void shouldFindExpectedAuthorByName() {
        val optionalActualAuthor = authorRepository.findByName(AUTHOR_NAME);
        assertThat(optionalActualAuthor).isPresent().get()
                .hasFieldOrPropertyWithValue("name",AUTHOR_NAME);
    }
}