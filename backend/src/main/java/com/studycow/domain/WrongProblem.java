package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <pre>
 *     성적 상세내역 도메인 클래스
 * </pre>
 * @author 노명환
 * @since JDK17
 */

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_wrong_detail")
public class WrongProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wrong_detail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "score_id", nullable = false)
    @NotNull
    private UserSubjectScore score;

    @ManyToOne
    @JoinColumn(name = "cat_code", nullable = false)
    @NotNull
    private ProblemCategory catCode;

    @Column(name = "wrong_cnt", nullable = false)
    @NotNull
    private int wrongCount;
}
