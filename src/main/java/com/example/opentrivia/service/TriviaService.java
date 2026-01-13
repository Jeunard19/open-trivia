package com.example.opentrivia.service;

import com.example.opentrivia.dto.request.QuestionsRequest;
import com.example.opentrivia.dto.response.OpenTriviaResponse;
import com.example.opentrivia.dto.response.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TriviaService implements ITriviaService{

    private final WebClient webClient = WebClient.create("https://opentdb.com");
    private final CacheService cacheService;

    @Override
    public List<Question> getQuestions() {
        OpenTriviaResponse response = webClient.get()
                .uri("/api.php?amount=50")
                .retrieve()
                .bodyToMono(OpenTriviaResponse.class)
                .block();
//        cacheService.saveOpenTriviaResponse("hello",response.getResults());
//        OpenTriviaResponse response2 = cacheService.getOpenTriviaResponse("hello");

        return response != null ? response.getResults() : List.of();
    }
}
