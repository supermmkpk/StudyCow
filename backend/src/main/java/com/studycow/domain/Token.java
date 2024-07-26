package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.time.LocalDateTime;

/**
 * 토큰 도메인 클래스
 * @author 채기훈
 * @since JDK17
 */

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
    private LocalDateTime endDate;

    @Column(name = "IN_DATE")
    private LocalDateTime inDate;

    @PrePersist
    public void onCreate(){
        this.inDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){

    }
}
