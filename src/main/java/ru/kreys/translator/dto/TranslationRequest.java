package ru.kreys.translator.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TranslationRequest(
        @Schema(description = "текст, который надо перевести")
        @NotBlank(message = "Текст не может быть пустым")
        String text,

        @Schema(description = "язык с которого надо перевести")
        @NotBlank(message = "Язык исходного сообщения не может быть пустым")
        String sourceLang,

        @Schema(description = "язык на который надо перевести")
        @NotBlank(message = "Требуемый язык не может быть пустым")
        String targetLang
) {
}
