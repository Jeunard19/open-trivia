package com.example.opentrivia.service;

import com.example.opentrivia.dto.response.OpenTriviaResponse;
import com.example.opentrivia.dto.response.TriviaQuestion;

import java.util.List;

public interface ICacheService {
    void saveOpenTriviaResponse(String token, List<TriviaQuestion> questions);
    List<TriviaQuestion> getOpenTriviaResponse(String token);
    void removeFirstQuestion(String key);

}
