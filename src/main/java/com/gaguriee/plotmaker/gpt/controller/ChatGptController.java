package com.gaguriee.plotmaker.gpt.controller;

import com.gaguriee.plotmaker.gpt.dto.ChatGptResponse;
import com.gaguriee.plotmaker.gpt.dto.PlotMakeRequest;
import com.gaguriee.plotmaker.gpt.service.ChatGptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/plot")
public class ChatGptController {
    private final ChatGptService chatGptService;

    public ChatGptController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @PostMapping("/test")
    public void test() {
        log.info("진입");
    }


    @PostMapping("/make")
    public ResponseEntity<ChatGptResponse> makePerfumeStory(@RequestBody final PlotMakeRequest plotMakeRequest) {
        log.info("Chat GPT에게 스토리 생성 요청, 질문 내용 : {}",plotMakeRequest.toPromptString());
        return ResponseEntity.ok(chatGptService.askQuestionToChatGpt(plotMakeRequest));
    }

}