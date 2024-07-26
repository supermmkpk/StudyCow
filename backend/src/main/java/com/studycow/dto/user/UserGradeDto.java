package com.studycow.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGradeDto {

    private int gradeCode;
    private String gradeName;
    private int minExp;
    private int maxExp;

}
