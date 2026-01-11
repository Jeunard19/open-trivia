package com.example.opentrivia.controller;

import com.example.opentrivia.service.TriviaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trivia")
@RequiredArgsConstructor
public class TriviaController {

    private final TriviaService triviaService;

}

