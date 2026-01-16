package com.example.opentrivia.service;

import com.example.opentrivia.dto.response.OpenTriviaResponse;
import com.example.opentrivia.dto.response.QuestionInfo;

import java.util.List;

public interface ICacheService {
    void saveOpenTriviaResponse(String token, List<QuestionInfo> questions);
    List<QuestionInfo> getOpenTriviaResponse(String token);
    public void removeFirstQuestion(String key);

}
