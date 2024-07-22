package com.studycow.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "t_wrong_detail")
public class WrongProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wrong_detail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "score_id")
    private UserSubjectScore score;

    @ManyToOne
    @JoinColumn(name = "cat_code")
    private ProblemCategory catCode;

    @Column(name = "wrong_cnt")
    private int wrongCount;
}
