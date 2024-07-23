package com.studycow.domain;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;

/**
 * 유저 경험치 도메인 클래스
 * @author 채기훈
 * @since JDK17
 */

@Entity
@Table(name = "t_exp_log", indexes =
@Index(name = "idx_getDate", columnList = "get_date"))
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
