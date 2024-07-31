package com.studycow.dto.score;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseScoreDto {
    private String subName;
    private int targetScore;
    private int targetGrade;
    private int maxScore;
    private String advice;
    private List<ScoreDto> scores;

    public ResponseScoreDto(String subName, int targetScore, int targetGrade, int maxScore){
        this.subName = subName;
        this.targetScore = targetScore;
        this.targetGrade = targetGrade;
        this.maxScore = maxScore;
        this.advice = "";
        this.scores = null;
    }

    @Override
    public String toString() {
        return "ResponseScoreDto{" +
                "subName='" + subName + '\'' +
                ", targetScore=" + targetScore +
                ", targetGrade=" + targetGrade +
                ", maxScore=" + maxScore +
                ", scores=" + scores +
                "}";
    }
}
