package com.studycow.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_subject_code")
@Getter @Setter
public class SubjectCode {

    @Id
    @Column(name = "sub_code")
    private int code;

    @Column(name = "sub_name", length = 20)
    private String name;

    @Column(name = "sub_max_score")
    private int maxScore;

    @Column(name = "sub_status")
    private int status;

    @Column(name = "sub_in_date")
    private LocalDateTime inDate;
}
