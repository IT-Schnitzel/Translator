package ru.kreys.translator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.kreys.translator.controller.TranslationController;
import ru.kreys.translator.dto.TranslationRequest;
import ru.kreys.translator.service.TranslationService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TranslationController.class)
public class TranslationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TranslationService translationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void translateTest() throws Exception {
        TranslationRequest translationRequest = new TranslationRequest("hello", "en", "ru");


        String translatedText = "привет";
        when(translationService.translate(any(TranslationRequest.class), anyString())).thenReturn(translatedText);

        mockMvc.perform(post("/api/v1/translate")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(translationRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(translatedText));
    }
}