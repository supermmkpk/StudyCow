package com.studycow.dto.score;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScoreDto {
    private Long scoreId;
    private int subCode;
    private String subName;
    private int testScore;
    private Integer testGrade;
    private LocalDate testDate;
    private List<ScoreDetailDto> scoreDetails;

    public ScoreDto(Long scoreId, int subCode, String subName,
                    int testScore, Integer testGrade, LocalDate testDate) {
        this.scoreId = scoreId;
        this.subCode = subCode;
        this.subName = subName;
        this.testScore = testScore;
        this.testGrade = testGrade;
        this.testDate = testDate;
        this.scoreDetails = null;
    }


}
