package com.gaguriee.plotmaker.gpt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class ChatGptRequest implements Serializable {

    private String model;
    private List<ChatMessage> messages;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    private Double temperature;
    @JsonProperty("top_p")
    private Double topP;

    public ChatGptRequest(){}
    @Builder
    public ChatGptRequest(String model, List<ChatMessage> messages, Integer maxTokens, Double temperature, Double topP) {
        this.model = model;

        this.messages = messages;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.topP = topP;
    }
}