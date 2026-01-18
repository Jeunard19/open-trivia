package com.example.opentrivia.controller;

import com.example.opentrivia.dto.request.AnswersCheckRequest;
import com.example.opentrivia.dto.response.AnswerCheckResponse;
import com.example.opentrivia.dto.response.QuestionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TriviaControllerIT{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testEndpoints() throws Exception {
        String firstQuestionJson = mockMvc.perform(get("/api/questions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.question").isNotEmpty())
                .andExpect(jsonPath("$.options").isArray())
                .andReturn()
                .getResponse()
                .getContentAsString();

        QuestionResponse firstQuestion = objectMapper.readValue(firstQuestionJson, QuestionResponse.class);
        assertNotNull(firstQuestion.getQuestion());
        assertTrue(firstQuestion.getOptions().size() > 1);

        AnswersCheckRequest answerRequest = new AnswersCheckRequest();
        answerRequest.setQuestion(firstQuestion.getQuestion());
        answerRequest.setAnswer(firstQuestion.getOptions().get(0));

        String answerJson = objectMapper.writeValueAsString(answerRequest);

        String responseJson = mockMvc.perform(post("/api/checkanswers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(answerJson))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.correct").isBoolean())
                .andReturn()
                .getResponse()
                .getContentAsString();

        AnswerCheckResponse answerResponse = objectMapper.readValue(responseJson, AnswerCheckResponse.class);
        assertNotNull(answerResponse);

        String nextQuestionJson = mockMvc.perform(get("/api/questions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        QuestionResponse nextQuestion = objectMapper.readValue(nextQuestionJson, QuestionResponse.class);
        
        assertNotEquals(firstQuestion.getQuestion(), nextQuestion.getQuestion(),
                "First question removed from cache");
    }
}

