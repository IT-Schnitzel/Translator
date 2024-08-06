package ru.kreys.translator.service;

public interface TranslatorWebService {

    /**
     * Интерфейс, который реализуют адаптеры к внешним системам для преводчика
     *
     * @param word       - слово, которое необходимо перевести
     * @param sourceLang - исходный язык
     * @param targetLang - язык, на который мы переводим слово
     * @return переведённое слово
     */
    String translateWord(String word, String sourceLang, String targetLang);
}
