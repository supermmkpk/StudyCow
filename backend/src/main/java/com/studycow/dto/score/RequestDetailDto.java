package com.studycow.dto.score;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 상세 성적 등록 요청 Dto
 * @author 노명환
 * @since JDK17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDetailDto {
    private int catCode;
    private int wrongCnt;
}
