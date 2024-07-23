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
 *     과목 코드 도메인 클래스
 * </pre>
 * @author 노명환
 * @since JDK17
 */

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_subject_code", indexes =
@Index(name = "idx_status", columnList = "sub_status"))
public class SubjectCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_code")
    private int code;

    @Column(name = "sub_name", length = 20)
    private String name;

    @Column(name = "sub_max_score", nullable = false)
    @NotNull
    private int maxScore;

    @Column(name = "sub_status", nullable = false)
    @NotNull
    private int status;

    @Column(name = "sub_in_date", nullable = false)
    @NotNull
    private LocalDateTime inDate;
}
