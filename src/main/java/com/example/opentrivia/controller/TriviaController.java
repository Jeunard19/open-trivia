package com.example.opentrivia.controller;

import com.example.opentrivia.dto.request.AnswersCheckRequest;
import com.example.opentrivia.dto.response.QuestionInfo;
import com.example.opentrivia.dto.response.AnswerCheckResponse;
import com.example.opentrivia.dto.response.QuestionPrompt;
import com.example.opentrivia.service.ITriviaService;
import com.example.opentrivia.service.TriviaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trivia")
@RequiredArgsConstructor
public class TriviaController {

    private final ITriviaService triviaService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public QuestionPrompt getQuestions() {
        return triviaService.getQuestions();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AnswerCheckResponse checkAnswer(@RequestBody AnswersCheckRequest answersCheckRequest) {
        String question = answersCheckRequest.getQuestion();
        String answer = answersCheckRequest.getAnswer();
        return triviaService.checkAnswer(question, answer);
    }

}

