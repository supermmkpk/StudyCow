package com.studycow.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlannerGetDto {
    private int planId;
    private int userId;
    private int subCode;
    private LocalDate planDate;
    private String planContent;
    private int planStudyTime;
    private int planStatus;
}
