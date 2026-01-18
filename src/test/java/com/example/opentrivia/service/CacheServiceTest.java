package com.example.opentrivia.service;

import com.example.opentrivia.dto.response.TriviaQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CacheServiceTest {

    @Mock
    private RedisTemplate<String, List<TriviaQuestion>> redisTemplate;

    @Mock
    private ValueOperations<String, List<TriviaQuestion>> valueOps;

    @InjectMocks
    private CacheService cacheService;

    @BeforeEach
    void setup() {
        when(redisTemplate.opsForValue()).thenReturn(valueOps);
    }

    @Test
    void testSaveQuestionsToCache() {
        List<TriviaQuestion> questions = List.of(new TriviaQuestion());

        cacheService.saveOpenTriviaResponse("questions", questions);

        verify(valueOps).set("questions", questions, Duration.ofMinutes(15));
    }

    @Test
    void testGetQuestionsFromCache() {
        List<TriviaQuestion> questions = List.of(new TriviaQuestion());

        when(valueOps.get("questions")).thenReturn(questions);

        List<TriviaQuestion> result =
                cacheService.getOpenTriviaResponse("questions");

        assertEquals(1, result.size());
    }

    @Test
    void testRemoveFirstQuestion() {
        TriviaQuestion q1 = new TriviaQuestion();
        TriviaQuestion q2 = new TriviaQuestion();

        List<TriviaQuestion> list = new ArrayList<>(List.of(q1, q2));
        when(valueOps.get("questions")).thenReturn(list);

        cacheService.removeFirstQuestion("questions");

        assertEquals(1, list.size());
        verify(valueOps).set(eq("questions"), anyList(),eq(Duration.ofMinutes(15)));
    }
}
