package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_subject_code")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectCode {

    @Id
    @Column(name = "sub_code")
    private int code;

    @Column(name = "sub_name", length = 20)
    private String name;

    @Column(name = "sub_max_score")
    @NotNull
    private int maxScore;

    @Column(name = "sub_status")
    @NotNull
    private int status;

    @Column(name = "sub_in_date")
    @NotNull
    private LocalDateTime inDate;
}
