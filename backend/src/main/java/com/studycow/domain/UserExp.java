package com.studycow.domain;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;

@Entity
@Table(name = "t_exp_log")
public class UserExp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="EXP_ID")
    private Long id;

    @Column(name = "GET_AMOUNT")
    private int getAmount;

    @Column(name = "GET_DATE")
    @CreationTimestamp
    private Timestamp getDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name= "USER_ID")
    private User user;
}
