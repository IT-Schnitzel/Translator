package ru.kreys.translator.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.kreys.translator.dto.TranslationRequest;
import ru.kreys.translator.service.TranslationService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TranslationController.class)
public class TranslationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TranslationService translationService;

    @Test
    public void translateTest() throws Exception {
        String translatedText = "translated text";
        when(translationService.translate(any(TranslationRequest.class), anyString())).thenReturn(translatedText);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/translate")
                        .param("text", "hello")
                        .param("sourceLang", "en")
                        .param("targetLang", "ru"))
                .andExpect(status().isOk())
                .andExpect(content().string(translatedText));
    }
}
