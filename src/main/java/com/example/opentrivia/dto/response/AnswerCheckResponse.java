package com.example.opentrivia.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnswerCheckResponse {
    private boolean correct;
}
