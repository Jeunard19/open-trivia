package com.example.opentrivia.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class QuestionPrompt {
    private String type;
    private String question;
    private List<String> options;
}
