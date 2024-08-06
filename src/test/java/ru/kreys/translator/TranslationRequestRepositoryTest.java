package com.example.translationapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class TranslationRequestRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TranslationRequestRepository translationRequestRepository;

    @Test
    public void saveTranslationRequestTest() {
        translationRequestRepository.saveTranslationRequest("127.0.0.1", "hello", "привет");

        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM translation_requests", Integer.class);
        assertThat(count).isEqualTo(1);
    }
}
