package ru.kreys.translator.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    public static final String REQUEST_EXECUTOR_SERVICE = "request-executor-service";
    public static final String YANDEX_TRANSLATOR_SERVICE = "yandex-translator-service";


    @Value("${yandex.api-key}")
    private final String apiKey;

    @Bean
    @Qualifier(REQUEST_EXECUTOR_SERVICE)
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(10);
    }


    @Bean
    @Qualifier(YANDEX_TRANSLATOR_SERVICE)
    public RestTemplate template(RestTemplateBuilder builder) {
        return builder.additionalRequestCustomizers(
                request -> request.getHeaders().set(
                        HttpHeaders.AUTHORIZATION, "Bearer " + apiKey
                )
        ).build();
    }


}
