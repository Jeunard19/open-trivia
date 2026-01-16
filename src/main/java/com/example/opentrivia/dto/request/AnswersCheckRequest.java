package com.example.opentrivia.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnswersCheckRequest {
    @JsonProperty("answer")
    String answer;
    @JsonProperty("question")
    String question;
}
