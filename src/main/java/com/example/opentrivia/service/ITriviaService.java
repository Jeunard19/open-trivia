package com.example.opentrivia.service;

import com.example.opentrivia.dto.request.AnswersCheckRequest;
import com.example.opentrivia.dto.request.QuestionsRequest;
import com.example.opentrivia.dto.response.AnswerCheckResponse;
import com.example.opentrivia.dto.response.OpenTriviaResponse;
import com.example.opentrivia.dto.response.QuestionInfo;
import com.example.opentrivia.dto.response.QuestionPrompt;

import java.util.List;

public interface ITriviaService  {

    QuestionPrompt getQuestions();

    AnswerCheckResponse checkAnswer(String questions, String answer);
}