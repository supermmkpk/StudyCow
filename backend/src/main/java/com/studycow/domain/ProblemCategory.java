package com.studycow.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_category")
@Getter @Setter
public class ProblemCategory {
    @Id
    @Column(name = "cat_code")
    private int code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_code")
    private int subCode;

    @Column(name = "cat_name", length = 30)
    private String name;

    @Column(name = "cat_status")
    private int status;

    @Column(name = "cat_in_date")
    private LocalDateTime inDate;
}
