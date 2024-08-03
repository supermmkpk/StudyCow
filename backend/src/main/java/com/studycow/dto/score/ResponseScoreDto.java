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
    private int subCode;
    private String subName;
    private int targetScore;
    private int targetGrade;
    private int maxScore;
    private String advice;
    private List<ScoreDto> scores;

    public ResponseScoreDto(int subCode, String subName, int targetScore, int targetGrade, int maxScore){
        this.subCode = subCode;
        this.subName = subName;
        this.targetScore = targetScore;
        this.targetGrade = targetGrade;
        this.maxScore = maxScore;
        this.advice = "";
        this.scores = null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseScoreDto{").append("\n");
        sb.append("subCode='").append(subCode).append("\n");
        sb.append("subName='").append(subName).append('\'').append("\n");
        sb.append("targetScore=").append(targetScore).append("\n");
        sb.append("targetGrade=").append(targetGrade).append("\n");
        sb.append("maxScore=").append(maxScore).append("\n");
        sb.append("scores=[").append("\n");
        for(ScoreDto score : scores){
            sb.append(score.toString());
        }
        sb.append("\n").append("]}");
        return sb.toString();
    }
}
