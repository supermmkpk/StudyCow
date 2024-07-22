package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_category")
public class ProblemCategory {

    @Id
    @Column(name = "cat_code")
    private int code;

    @Column(name = "cat_name", length = 30)
    private String name;

    @Column(name = "cat_status", nullable = false)
    @NotNull
    private int status;

    @Column(name = "cat_in_date", nullable = false)
    @NotNull
    private LocalDateTime inDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_code", nullable = false)
    @NotNull
    private SubjectCode subjectCode;
}
