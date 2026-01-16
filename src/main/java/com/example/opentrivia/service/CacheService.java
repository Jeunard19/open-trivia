package com.example.opentrivia.service;

import com.example.opentrivia.dto.response.OpenTriviaResponse;
import com.example.opentrivia.dto.response.QuestionInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CacheService implements ICacheService {

    private final RedisTemplate<String, List<QuestionInfo> > redisTemplate;

    private static final Duration TTL = Duration.ofMinutes(15);

    @Override
    public void saveOpenTriviaResponse(String token, List<QuestionInfo> questions) {
        String key = "quiz:" + token;
        redisTemplate.opsForValue().set(key, questions, TTL);
    }

    @Override
    public List<QuestionInfo> getOpenTriviaResponse(String token) {
        String key = "quiz:" + token;
        Object obj = redisTemplate.opsForValue().get(key);

        if (obj == null) return null;
        return (List<QuestionInfo> ) obj;
    }

    public void removeFirstQuestion(String key) {
        List<QuestionInfo> cached = getOpenTriviaResponse(key);
        if (!cached.isEmpty()) {
            cached.remove(0);        // remove first item
            saveOpenTriviaResponse(key, cached); // update cache
        }
    }


}


