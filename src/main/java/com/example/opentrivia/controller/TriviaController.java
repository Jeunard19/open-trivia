package com.example.opentrivia.controller;

import com.example.opentrivia.dto.response.QuestionInfo;
import com.example.opentrivia.dto.response.QuestionPrompt;
import com.example.opentrivia.service.ITriviaService;
import com.example.opentrivia.service.TriviaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trivia")
@RequiredArgsConstructor
public class TriviaController {

    private final ITriviaService triviaService;

    @GetMapping()
    public QuestionPrompt getQuestions() {
        return triviaService.getQuestions();
    }

}

