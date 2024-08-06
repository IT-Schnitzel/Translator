package ru.kreys.translator.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.kreys.translator.dto.TranslationResponse;
import ru.kreys.translator.dto.YandexRequest;

import java.util.List;

import static ru.kreys.translator.config.AppConfig.YANDEX_TRANSLATOR_SERVICE;

@Component
@RequiredArgsConstructor
public class YandexWebService implements TranslatorWebService {


    public static final String YANDEX_API = "https://translate.api.cloud.yandex.net/translate/v2/translate";

    @Qualifier(YANDEX_TRANSLATOR_SERVICE)
    private final RestTemplate restTemplate;


    @Value("${yandex.folder-id}")
    private final String folderId;


    @Override
    public String translateWord(String word, String sourceLang, String targetLang) {


        var request = YandexRequest.builder()
                .sourceLanguageCode(sourceLang)
                .targetLanguageCode(targetLang)
                .texts(List.of(word))
                .speller(false)
                .folderId(folderId)
                .build();


        TranslationResponse response = restTemplate.postForObject(YANDEX_API, request, TranslationResponse.class);

        //todo узнать как узнавать status code ошибки и как-нибудь обрабатывать её
        return response.getTranslations().getFirst().getText();


    }
}
