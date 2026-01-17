package com.example.opentrivia.service;

import com.example.opentrivia.dto.response.AnswerCheckResponse;
import com.example.opentrivia.dto.response.QuestionResponse;

import java.util.List;

public interface ITriviaService  {

    QuestionResponse getQuestions();

    AnswerCheckResponse checkAnswer(String questions, String answer);
}