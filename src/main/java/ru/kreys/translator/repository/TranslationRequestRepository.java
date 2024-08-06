package ru.kreys.translator.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TranslationRequestRepository {

    private final JdbcClient jdbcClient;


    public void saveTranslationRequest(String ipAddress, String inputText, String translatedText) {
        String sql = "INSERT INTO translation_requests (ip_address, input_text, translated_text) VALUES (?, ?, ?) RETURNING id";
        jdbcClient.sql(sql)
                .param(ipAddress)
                .param(inputText)
                .param(translatedText)
                .query(Long.class)
                .optional();
    }

}
