package ru.kreys.translator.dto;

import lombok.Data;

@Data
public class TranslationsItem {
    private String text;
    private String detectedLanguageCode;
}