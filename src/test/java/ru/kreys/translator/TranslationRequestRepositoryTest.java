package ru.kreys.translator.repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import static org.assertj.core.api.Assertions.assertThat;
@JdbcTest
@ContextConfiguration(classes = TranslationRequestRepositoryTest.Config.class)
public class TranslationRequestRepositoryTest {

    @Autowired
    private TranslationRequestRepository translationRequestRepository;

    @Autowired
    private JdbcClient jdbcClient;

    @BeforeEach
    public void setUp() {
        jdbcClient.sql("CREATE TABLE translation_requests (id BIGINT AUTO_INCREMENT PRIMARY KEY, ip_address VARCHAR(255), input_text VARCHAR(255), translated_text VARCHAR(255))").execute();
    }

    @Test
    public void saveTranslationRequestTest() {
        translationRequestRepository.saveTranslationRequest("127.0.0.1", "hello", "привет");

        Long count = jdbcClient.sql("SELECT COUNT(*) FROM translation_requests").query(Long.class).optional().orElse(0L);
        assertThat(count).isEqualTo(1L);
    }

    @Configuration
    static class Config {
        @Bean
        public JdbcClient jdbcClient() {
            return JdbcClient.create();
        }

        @Bean
        public TranslationRequestRepository translationRequestRepository(JdbcClient jdbcClient) {
            return new TranslationRequestRepository(jdbcClient);
        }
    }
}
