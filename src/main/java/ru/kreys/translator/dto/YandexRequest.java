package ru.kreys.translator.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class YandexRequest {
    private List<String> texts;
    private String format;
    private boolean speller;
    private String sourceLanguageCode;
    private String model;
    private String targetLanguageCode;
    private String folderId;
    private GlossaryConfig glossaryConfig;
}