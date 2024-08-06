package ru.kreys.translator.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.kreys.translator.dto.TranslationRequest;
import ru.kreys.translator.exceptions.UnkownException;
import ru.kreys.translator.repository.TranslationRequestRepository;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static ru.kreys.translator.config.AppConfig.REQUEST_EXECUTOR_SERVICE;

@Service
@RequiredArgsConstructor
@Slf4j
public class TranslationService {

    private final TranslatorWebService translatorWebService;

    private final TranslationRequestRepository translationRequestRepository;


    @Qualifier(REQUEST_EXECUTOR_SERVICE)
    private final ExecutorService executorService;

    public String translate(TranslationRequest translationRequest, String remoteAddr) {
        log.debug("Пришёл запрос на перевод {}", translationRequest);

        String[] words = translationRequest.text().split(" ");
        var futures = Arrays.stream(words)
                .map(word ->
                        executorService.submit(() ->
                                translatorWebService.translateWord(word,
                                        translationRequest.sourceLang(),
                                        translationRequest.targetLang()))
                ).toList();

        StringBuilder translatedText = new StringBuilder(words.length);
        for (Future<String> future : futures) {
            try {
                translatedText.append(future.get()).append(" ");
            } catch (Exception e) {
                log.error("Не удалось перевести предложение");
                throw new UnkownException("Не удалось перевести предложение");
            }
        }


        String response = translatedText.toString();
        translationRequestRepository.saveTranslationRequest(remoteAddr, translationRequest.text(), response);
        return response.trim();
    }
}
