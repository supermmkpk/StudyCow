package com.studycow.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String planDate;

    private String planContent;

    private int planStudyTime;

    private int planStatus;


}
