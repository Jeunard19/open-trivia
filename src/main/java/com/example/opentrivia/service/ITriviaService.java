package com.example.opentrivia.service;

import com.example.opentrivia.dto.request.QuestionsRequest;
import com.example.opentrivia.dto.response.OpenTriviaResponse;
import com.example.opentrivia.dto.response.Question;

import java.util.List;

public interface ITriviaService  {

    List<Question> getQuestions();
}