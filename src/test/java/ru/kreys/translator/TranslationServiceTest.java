package ru.kreys.translator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TranslationServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TranslationService translationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        translationService = new TranslationService();
    }

    @Test
    public void translateWordTest() {
        String mockResponse = "{ \"code\": 200, \"lang\": \"en-ru\", \"text\": [\"привет\"] }";
        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(mockResponse);

        String translatedWord = translationService.translateWord("hello", "en", "ru");
        assertEquals("привет", translatedWord);
    }
}

