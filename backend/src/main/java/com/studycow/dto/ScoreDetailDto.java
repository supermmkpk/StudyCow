package com.studycow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScoreDetailDto {
    private int wrongDetailId;
    private int scoreId;
    private int catCode;
    private String catName;
    private int wrongCnt;
}
