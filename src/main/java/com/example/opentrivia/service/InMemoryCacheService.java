package com.example.opentrivia.service;

import com.example.opentrivia.dto.response.TriviaQuestion;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("!redis")
public class InMemoryCacheService implements ICacheService {

    private final List<TriviaQuestion> questions = new ArrayList<>();

    @Override
    public void saveOpenTriviaResponse(String key, List<TriviaQuestion> data) {
        questions.clear();
        questions.addAll(data);
    }

    @Override
    public List<TriviaQuestion> getOpenTriviaResponse(String key) {
        return questions;
    }

    @Override
    public void removeFirstQuestion(String key) {
        if (!questions.isEmpty()) {
            questions.remove(0);
        }
    }
}
