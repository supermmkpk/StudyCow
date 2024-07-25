package com.studycow.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 플래너 조회용 Dto
 * @author 채기훈
 * @since JDK17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlannerGetDto {
    /** 플래너 id **/
    private int planId;
    /** 생성한 유저 **/
    private int userId;
    /** 선택한 과목 **/
    private int subCode;
    /** 플래너 등록 일자**/
    private LocalDate planDate;
    /** 플래너 내용 **/
    private String planContent;
    /** 플래너 목표 공부 시간 **/
    private int planStudyTime;
    /** 플래너 완료 여부 **/
    private int planStatus;
}
