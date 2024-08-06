package com.example.translationapp;

import com.example.translationapp.model.TranslationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(TranslationController.class)
public class TranslationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TranslationService translationService;

    @MockBean
    private TranslationRequestRepository translationRequestRepository;

    @Test
    public void showFormTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/translate"))
                .andExpect(status().isOk())
                .andExpect(view().name("translate"));
    }

    @Test
    public void translateTest() throws Exception {
        when(translationService.translateText(any(String.class), any(String.class), any(String.class))).thenReturn("translated text");

        mockMvc.perform(MockMvcRequestBuilders.post("/translate")
                        .param("text", "hello")
                        .param("sourceLang", "en")
                        .param("targetLang", "ru"))
                .andExpect(status().isOk())
                .andExpect(view().name("translate"));
    }
}
