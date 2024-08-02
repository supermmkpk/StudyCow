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
    /** 오답 내역 ID */
    private Long wrongDetailId;
    /** 점수 ID */
    private Long scoreId;
    /** 오답 유형 코드 */
    private int catCode;
    /** 오답 유형명 */
    private String catName;
    /** 오답 문항 개수 */
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
