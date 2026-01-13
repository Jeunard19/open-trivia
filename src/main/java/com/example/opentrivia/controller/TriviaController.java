package com.example.opentrivia.controller;

import com.example.opentrivia.dto.response.Question;
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
    public List<Question> getQuestions() {
        return triviaService.getQuestions();
    }

}

