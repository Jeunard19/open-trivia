package com.example.opentrivia.service;
import com.example.opentrivia.dto.response.AnswerCheckResponse;
import com.example.opentrivia.dto.response.QuestionResponse;
import com.example.opentrivia.dto.response.TriviaQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TriviaServiceTest {
    @Mock
    private CacheService cacheService;

    @InjectMocks
    private TriviaService triviaService;

    @Test
    void testGetQuestion() {
        TriviaQuestion triviaQuestion = new TriviaQuestion();
        triviaQuestion.setQuestion("What is 2 + 2?");
        triviaQuestion.setCorrect_answer("4");
        triviaQuestion.setIncorrect_answers(List.of("3", "5", "6"));

        when(cacheService.getOpenTriviaResponse("questions"))
                .thenReturn(List.of(triviaQuestion));

        QuestionResponse result = triviaService.getQuestions();

        assertEquals("What is 2 + 2?", result.getQuestion());
        assertEquals(4, result.getOptions().size());

        verify(cacheService).getOpenTriviaResponse("questions");
    }

    @Test
    void testCheckAnswer_returnsTrue() {
        TriviaQuestion triviaQuestion = new TriviaQuestion();
        triviaQuestion.setQuestion("Capital of France?");
        triviaQuestion.setCorrect_answer("Paris");

        when(cacheService.getOpenTriviaResponse("questions"))
                .thenReturn(List.of(triviaQuestion));


        AnswerCheckResponse response =
                triviaService.checkAnswer("Capital of France?",
                        "Paris");

        assertTrue(response.isCorrect());
    }

    @Test
    void testCheckAnswer_returnsFalse() {
        TriviaQuestion triviaQuestion = new TriviaQuestion();
        triviaQuestion.setQuestion("Capital of France?");
        triviaQuestion.setCorrect_answer("Paris");

        when(cacheService.getOpenTriviaResponse("questions"))
                .thenReturn(List.of(triviaQuestion));

        AnswerCheckResponse response =
                triviaService.checkAnswer("Capital of France?",
                        "London");

        assertFalse(response.isCorrect());
    }

    @Test
    void testCheckAnswer_removesFirstQuestion() {
        TriviaQuestion triviaQuestion = new TriviaQuestion();
        triviaQuestion.setQuestion("Capital of France?");
        triviaQuestion.setCorrect_answer("Paris");
        triviaQuestion.setIncorrect_answers(List.of("London","Berlin","Madrid"));

        List<TriviaQuestion> questions = new ArrayList<>();
        questions.add(triviaQuestion);

        when(cacheService.getOpenTriviaResponse("questions"))
                .thenReturn(questions);

        triviaService.checkAnswer("Capital of France?",
                "Paris");
        verify(cacheService).removeFirstQuestion("questions");
    }
}
