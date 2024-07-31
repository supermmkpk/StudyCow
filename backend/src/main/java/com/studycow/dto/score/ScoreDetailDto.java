package com.studycow.dto.score;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScoreDetailDto {
    private Long wrongDetailId;
    private Long scoreId;
    private int catCode;
    private String catName;
    private int wrongCnt;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{").append("\n");
        sb.append("scoreId=").append(scoreId).append("\n");
        sb.append("catName='").append(catName).append('\'').append("\n");
        sb.append("wrongCnt=").append(wrongCnt).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
