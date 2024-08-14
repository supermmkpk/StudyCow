package com.studycow.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 플래너 정보 상세 조회 Dto
 *
 * @author 채기훈
 * @since JDK17
 */

public record PlannerGetDto(    int planId,
        int userId,
        int subCode,
        LocalDate planDate,
        String planContent,
        int planStudyTime,
        int planStatus) {
}
