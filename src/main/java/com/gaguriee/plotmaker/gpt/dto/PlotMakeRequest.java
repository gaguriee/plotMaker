package com.gaguriee.plotmaker.gpt.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class PlotMakeRequest implements Serializable {
    private String genre;


    private String target;

    public PlotMakeRequest(){}

    @Builder
    public PlotMakeRequest(String genre, String scentAnswer, String target, String seasonAnswer, String styleAnswer) {
        this.genre = genre;
        this.target = target;
    }

    public String toPromptString() {
        return "제가 제시할 단어는 다음과 같습니다. " +
                "장르: " + genre +
                ", 대상 관객: " + target + "." +
                "해당 키워드를 조합해서 2000byte 이내의 스토리를 만들어주세요";
    }
}