package com.studycow.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <pre>
 *     유저 플랜 도메인 클래스
 * </pre>
 * @author 노명환
 * @since JDK17
 */

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_plan", indexes =
@Index(name = "idx_planDate", columnList = "plan_date"))
public class UserSubjectPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_code", nullable = false)
    @NotNull
    private SubjectCode subCode;

    @Column(name = "plan_date", nullable = false)
    @NotNull
    private LocalDate planDate;

    @Column(name = "plan_content")
    private String content;

    @Column(name = "plan_study_time", nullable = false)
    @ColumnDefault("0")
    @NotNull
    private int studyTime;

    @Column(name = "plan_in_date", nullable = false)
    @NotNull
    private LocalDateTime inDate;

    @Column(name = "plan_status", nullable = false)
    @ColumnDefault("0")
    @NotNull
    private int status;

    @Column(name = "plan_update_date", nullable = false)
    @NotNull
    private LocalDateTime updateDate;

    @Column(name = "plan_sum_time", nullable = false)
    @ColumnDefault("0")
    @NotNull
    private int sumTime;
}

