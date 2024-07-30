package com.studycow.dto.score;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * 성적 등록 요청 Dto
 * @author 노명환
 * @since JDK17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestScoreDto {
    private int subCode;
    private LocalDate testDate;
    private int testScore;
    private int testGrade;
    private List<RequestDetailDto> scoreDetails;
}
