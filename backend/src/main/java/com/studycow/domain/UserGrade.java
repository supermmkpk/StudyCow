package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "t_grade_code")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserGrade {

    @Id
    @Column(name = "GRADE_CODE",nullable = false)
    @NotNull
    private Integer gradeCode;

    @Column(name = "GRADE_NAME", length = 10,nullable = false)
    @NotNull
    private String gradeName;

    @Column(name = "MIN_EXP",nullable = false)
    @ColumnDefault("0")
    @NotNull
    private Integer minEXP;

    @Column(name = "MAX_EXP",nullable = false)
    @ColumnDefault("100")
    @NotNull
    private Integer maxEXP;

}
