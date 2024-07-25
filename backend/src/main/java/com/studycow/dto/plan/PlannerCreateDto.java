package com.studycow.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 플래너 생성 Dto
 * @author 채기훈
 * @since JDK17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlannerCreateDto {

    private int subCode;

    private LocalDate planDate;

    private String planContent;

    private int planStudyTime;

    private int planStatus;


}
