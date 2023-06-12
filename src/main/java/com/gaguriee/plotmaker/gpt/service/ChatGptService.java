package com.gaguriee.plotmaker.gpt.service;

import com.gaguriee.plotmaker.config.ChatGptConfig;
import com.gaguriee.plotmaker.gpt.dto.ChatGptRequest;
import com.gaguriee.plotmaker.gpt.dto.ChatGptResponse;
import com.gaguriee.plotmaker.gpt.dto.ChatMessage;
import com.gaguriee.plotmaker.gpt.dto.PlotMakeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatGptService {
    @Value("${gpt.apiKey}")
    private String API_KEY;
    private static RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<ChatGptRequest> createHttpEntity(ChatGptRequest chatGptRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + API_KEY);
        return new HttpEntity<>(chatGptRequest, headers);
    }

    public ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatGptRequest) {
        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequest,
                ChatGptResponse.class);
        if (isGptCannotResponse(responseEntity)) {
            throw new RuntimeException();
        }
        return responseEntity.getBody();
    }

    public ChatGptResponse askQuestionToChatGpt(PlotMakeRequest plotMakeRequest) {
        System.out.println("====도달====");
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage("user", plotMakeRequest.toPromptString()));
        return this.getResponse(
                this.createHttpEntity(
                        ChatGptRequest.builder()
                                .model(ChatGptConfig.MODEL)
                                .messages(messages)
                                .maxTokens(ChatGptConfig.MAX_TOKEN)
                                .temperature(ChatGptConfig.TEMPERATURE)
                                .topP(ChatGptConfig.TOP_P)
                                .build()));
    }

    public boolean isGptCannotResponse(HttpEntity<ChatGptResponse> chatGptResponseEntity) {
        if (chatGptResponseEntity.getBody().getChoices() == null) {
            return true;
        }
        return false;
    }
}