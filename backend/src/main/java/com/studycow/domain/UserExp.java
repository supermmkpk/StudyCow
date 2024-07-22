package com.studycow.domain;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;

@Entity
@Table(name = "")
public class UserExp {

    @Id
    @GeneratedValue
    private Long expId;

    @JoinColumn(name= "USER_ID")
    private int userId;

    @Column(name = "GET_AMOUNT")
    private int getAmount;

    @Column(name = "GET_DATE")
    @CreationTimestamp
    private Timestamp getDate;

}
