package com.gaguriee.plotmaker.gpt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
@Getter
public class Choice implements Serializable {
    /** Response Type
     *
     * "choices": [{
     *     "index": 0,
     *     "message": {
     *       "role": "assistant",
     *       "content": "\n\nHello there, how may I assist you today?",
     *     },
     *     "finish_reason": "stop"
     *   }],
     */

    private Integer index;
    private ChatMessage message;
    @JsonProperty("finish_reason")
    private String finishReason;

    public Choice(){}

    @Builder
    public Choice(ChatMessage message, Integer index, String finishReason) {
        this.message = message;
        this.index = index;
        this.finishReason = finishReason;
    }
}
