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
    private LocalDateTime scoreUpdateDate;
    private List<ScoreDetailDto> scoreDetails;
}
