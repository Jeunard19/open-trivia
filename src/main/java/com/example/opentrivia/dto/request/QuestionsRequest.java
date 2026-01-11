package com.example.opentrivia.dto.request;

import com.example.opentrivia.enums.Category;
import com.example.opentrivia.enums.Difficulty;
import com.example.opentrivia.enums.QuestionType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionsRequest {

    @NotBlank(message = "At least 1 question is required")
    @Min(1)
    @Max(50)
    private int questions;

    private Category category;

    private Difficulty difficulty;

    private QuestionType type;

}
