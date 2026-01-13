package com.example.opentrivia.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OpenTriviaResponse {
    private int response_code;
    private List<Question> results;
}
