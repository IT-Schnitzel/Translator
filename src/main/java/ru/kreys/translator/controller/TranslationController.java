package ru.kreys.translator.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kreys.translator.dto.TranslationRequest;
import ru.kreys.translator.service.TranslationService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TranslationController {
    //todo сделать RestControllerAdvice


    private final TranslationService translationService;


    @PostMapping("/translate")
    public String translate(@Valid TranslationRequest translationRequest, HttpServletRequest request) {

        return translationService.translate(translationRequest, request.getRemoteAddr());
    }
}
