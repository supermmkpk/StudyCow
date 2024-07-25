package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <pre>
 *     과목별 성적 도메인 클래스
 * </pre>
 * @author 노명환
 * @since JDK17
 */

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_score", indexes =
@Index(name = "idx_testDate", columnList = "test_date"))
public class UserSubjectScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_code", nullable = false)
    @NotNull
    private SubjectCode subjectCode;

    @Column(name = "test_date", nullable = false)
    @NotNull
    private LocalDate testDate;

    @Column(name = "test_score", nullable = false)
    @NotNull
    private int testScore;

    @Column(name = "test_grade")
    private Integer testGrade;

    @Column(name = "score_update_date", nullable = false)
    @NotNull
    private LocalDateTime updateDate;
}
/*
임시 성적 더미데이터
insert into t_score(user_id, sub_code, test_date, test_score, test_grade, score_update_date) values
(1, 1, '2024-07-01', 80, 3, current_timestamp()),
(1, 1, '2024-07-02', 85, 2, current_timestamp()),
(1, 1, '2024-07-03', 90, 2, current_timestamp()),
(1, 1, '2024-07-04', 95, 1, current_timestamp()),
(1, 1, '2024-07-05', 100, 1, current_timestamp());

insert into t_score(user_id, sub_code, test_date, test_score, test_grade, score_update_date) values
(1, 2, '2024-07-01', 70, 3, current_timestamp()),
(1, 2, '2024-07-02', 73, null, current_timestamp()),
(1, 2, '2024-07-03', 80, 2, current_timestamp()),
(1, 2, '2024-07-04', 75, 3, current_timestamp()),
(1, 2, '2024-07-05', 83, null, current_timestamp());

select * from t_score
where user_id = 1;
 */