package com.example.opentrivia.service;

import com.example.opentrivia.dto.request.QuestionsRequest;
import com.example.opentrivia.dto.response.OpenTriviaResponse;
import com.example.opentrivia.dto.response.QuestionInfo;
import com.example.opentrivia.dto.response.QuestionPrompt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TriviaService implements ITriviaService{

    private final WebClient webClient = WebClient.create("https://opentdb.com");
    private final CacheService cacheService;

    @Override
    public QuestionPrompt getQuestions() {
        List<QuestionInfo> cachedQuestions = cacheService.getOpenTriviaResponse("questions");
        if (cachedQuestions != null && !cachedQuestions.isEmpty()) {
            cacheService.saveOpenTriviaResponse("questions",cachedQuestions);
            cacheService.removeFirstQuestion("questions");
            System.err.println(cachedQuestions);
            return mapToQuestionPrompt( cachedQuestions.get(0));
        }
        OpenTriviaResponse response = webClient.get()
                .uri("/api.php?amount=3")
                .retrieve()
                .bodyToMono(OpenTriviaResponse.class)
                .block();
        cachedQuestions = response != null ? response.getResults() : null;
        cacheService.saveOpenTriviaResponse("questions",cachedQuestions);
        cacheService.removeFirstQuestion("questions");

        return mapToQuestionPrompt(cachedQuestions != null ? cachedQuestions.get(0) : null);
    }

    private QuestionPrompt mapToQuestionPrompt(QuestionInfo question){
        QuestionPrompt questionPrompt = new QuestionPrompt();
        questionPrompt.setQuestion(question.getQuestion());

        List<String> options = new ArrayList<>(question.getIncorrect_answers());
        options.add(question.getCorrect_answer());
        options.sort(String::compareToIgnoreCase);
        questionPrompt.setOptions(options);

        questionPrompt.setOptions(options);
        return questionPrompt;
    }
}
