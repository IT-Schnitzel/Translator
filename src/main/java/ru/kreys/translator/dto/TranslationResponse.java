package ru.kreys.translator.dto;

import lombok.Data;

import java.util.List;

@Data
public class TranslationResponse {
    private List<TranslationsItem> translations;
}