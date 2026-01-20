package com.example.opentrivia.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
public class QuestionResponse {
    private String question;
    private List<String> options;
}
