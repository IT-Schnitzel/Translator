package ru.kreys.translator.dto;

import lombok.Data;

@Data
public class GlossaryPairsItem {
    private String translatedText;
    private String sourceText;
    private boolean exact;
}