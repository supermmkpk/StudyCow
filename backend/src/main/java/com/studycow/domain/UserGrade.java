package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

/**
 * 유저 등급 도메인 클래스
 * @author 채기훈
 * @since JDK17
 */

@Entity
@Table(name = "t_grade_code")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UserGrade {

    @Id
    @Column(name = "GRADE_CODE",nullable = false)
    @NotNull
    private int gradeCode;

    @Column(name = "GRADE_NAME", length = 10, nullable = false)
    @NotNull
    private String gradeName;

    @Column(name = "MIN_EXP",nullable = false)
    @ColumnDefault("0")
    @NotNull
    private int minEXP;

    @Column(name = "MAX_EXP",nullable = false)
    @ColumnDefault("100")
    @NotNull
    private int maxEXP;
}



