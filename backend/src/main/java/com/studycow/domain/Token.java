package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Entity
@Table(name = "t_user_token")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    @Id
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "USER_ID",nullable = false)
    @NotNull
    private User user;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    @Column(name = "END_DATE")
    private Timestamp endDate;

    @Column(name = "IN_DATE")
    private Timestamp inDate;

}
