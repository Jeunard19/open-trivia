package com.example.opentrivia.service;

import com.example.opentrivia.dto.response.OpenTriviaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class CacheService implements ICacheService {

    private final RedisTemplate<String, OpenTriviaResponse> redisTemplate;

    private static final Duration TTL = Duration.ofMinutes(15);

    @Override
    public void saveOpenTriviaResponse(String token, OpenTriviaResponse quiz) {
        String key = "quiz:" + token;
        redisTemplate.opsForValue().set(key, quiz, TTL);
    }

    @Override
    public OpenTriviaResponse getOpenTriviaResponse(String token) {
        String key = "quiz:" + token;
        Object obj = redisTemplate.opsForValue().get(key);

        if (obj == null) return null;
        return (OpenTriviaResponse) obj;
    }


}


