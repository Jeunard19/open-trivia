package com.example.opentrivia.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AnswersRequest {

    @NotEmpty(message = "Answers list cannot be empty")
    private List<String> answers;

}