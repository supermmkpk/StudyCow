package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 *     유저 목표 도메인 클래스
 * </pre>
 * @author 노명환
 * @since JDK17
 */

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user_target", uniqueConstraints =
@UniqueConstraint(name = "uni_user_sub_code", columnNames = {"user_id", "sub_code"}))
public class UserScoreTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "target_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_code", nullable = false)
    @NotNull
    private SubjectCode subjectCode;

    @Column(name = "target_score", nullable = false)
    @NotNull
    private int targetScore;

    @Column(name = "target_grade", nullable = false)
    @NotNull
    private int targetGrade;
}
