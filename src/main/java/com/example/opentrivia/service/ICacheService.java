package com.example.opentrivia.service;

import com.example.opentrivia.dto.response.OpenTriviaResponse;

import java.util.List;

public interface ICacheService {
    void saveOpenTriviaResponse(String token, OpenTriviaResponse quiz);
    OpenTriviaResponse getOpenTriviaResponse(String token);



}
