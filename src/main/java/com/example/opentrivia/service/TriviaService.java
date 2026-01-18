package com.example.opentrivia.service;

import com.example.opentrivia.dto.response.AnswerCheckResponse;
import com.example.opentrivia.dto.response.OpenTriviaResponse;
import com.example.opentrivia.dto.response.QuestionResponse;
import com.example.opentrivia.dto.response.TriviaQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TriviaService implements ITriviaService{

    private final WebClient webClient = WebClient.create("https://opentdb.com");
    private final CacheService cacheService;

    @Override
    public QuestionResponse getQuestions() {
        List<TriviaQuestion> cachedQuestions = cacheService.getOpenTriviaResponse("questions");
        if (cachedQuestions != null && !cachedQuestions.isEmpty()) {
            cacheService.saveOpenTriviaResponse("questions",cachedQuestions);
            return mapToQuestionPrompt( cachedQuestions.get(0));
        }
        OpenTriviaResponse response = webClient.get()
                .uri("/api.php?amount=50")
                .retrieve()
                .bodyToMono(OpenTriviaResponse.class)
                .block();
        cachedQuestions = response != null ? response.getResults() : null;
        cacheService.saveOpenTriviaResponse("questions",cachedQuestions);
        return mapToQuestionPrompt(cachedQuestions != null ? cachedQuestions.get(0) : null);
    }

    @Override
    public AnswerCheckResponse checkAnswer(String question, String answer) {
        AnswerCheckResponse answerCheckResponse = new AnswerCheckResponse();
        answerCheckResponse.setCorrect(false);

        List<TriviaQuestion> cachedQuestions =
                new ArrayList<>(cacheService.getOpenTriviaResponse("questions"));

        cacheService.removeFirstQuestion("questions");
        Optional<TriviaQuestion> questionInfo = cachedQuestions.stream()
                .filter(q-> q.getQuestion().equals(question)).findFirst();
        if(questionInfo.isPresent() && questionInfo.get().
                getCorrect_answer().equals(answer)){
            answerCheckResponse.setCorrect(true);
            return answerCheckResponse;
        }
        return answerCheckResponse;
    }

    private QuestionResponse mapToQuestionPrompt(TriviaQuestion question){
        QuestionResponse questionPrompt = new QuestionResponse();
        questionPrompt.setQuestion(question.getQuestion());

        List<String> options = new ArrayList<>(question.getIncorrect_answers());
        options.add(question.getCorrect_answer());
        options.sort(String::compareToIgnoreCase);
        questionPrompt.setOptions(options);

        questionPrompt.setOptions(options);
        return questionPrompt;
    }
}
