package com.example.opentrivia.controller;

import com.example.opentrivia.dto.request.AnswersCheckRequest;
import com.example.opentrivia.dto.response.AnswerCheckResponse;
import com.example.opentrivia.dto.response.QuestionResponse;
import com.example.opentrivia.service.ITriviaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TriviaController {

    private final ITriviaService triviaService;

    @GetMapping("/questions")
    @ResponseStatus(HttpStatus.OK)
    public QuestionResponse getQuestions() {
        return triviaService.getQuestions();
    }

    @PostMapping("/checkanswers")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AnswerCheckResponse checkAnswer(@RequestBody AnswersCheckRequest answersCheckRequest) {
        String question = answersCheckRequest.getQuestion();
        String answer = answersCheckRequest.getAnswer();
        return triviaService.checkAnswer(question, answer);
    }

}

