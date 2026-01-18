package com.example.opentrivia.service;

import com.example.opentrivia.dto.response.TriviaQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CacheService implements ICacheService {

    private final RedisTemplate<String, List<TriviaQuestion> > redisTemplate;

    private static final Duration TTL = Duration.ofMinutes(15);

    @Override
    public void saveOpenTriviaResponse(String token, List<TriviaQuestion> questions) {
        redisTemplate.opsForValue().set(token, questions, TTL);
    }

    @Override
    public List<TriviaQuestion> getOpenTriviaResponse(String token) {
        Object obj = redisTemplate.opsForValue().get(token);

        if (obj == null) return null;
        return (List<TriviaQuestion> ) obj;
    }

    public void removeFirstQuestion(String key) {
        List<TriviaQuestion> cached = getOpenTriviaResponse(key);
        if (!cached.isEmpty()) {
            cached.remove(0);        // remove first item
            saveOpenTriviaResponse(key, cached); // update cache
        }
    }


}


