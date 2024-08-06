package ru.kreys.translator.dto;

import lombok.Data;

import java.util.List;

@Data
public class GlossaryData {
    private List<GlossaryPairsItem> glossaryPairs;
}