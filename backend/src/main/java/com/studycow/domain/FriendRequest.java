package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.time.LocalDateTime;

/**
 * 친구 요청 도메인 클래스
 * @author 채기훈
 * @Since JDK17
 */


@Entity
@Table(name = "t_friend_request")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FRIEND_REQUEST_ID",nullable = false)
    @NotNull
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FROM_USER_ID",nullable = false)
    @NotNull
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TO_USER_ID",nullable = false)
    @NotNull
    private User toUser;

    @Column(name = "REQUEST_STATUS",nullable = false)
    @NotNull
    @ColumnDefault("0")
    private int requestStatus;

    @Column(name = "REQUEST_DATE",nullable = false)
    @NotNull
    @CreationTimestamp
    private LocalDateTime requestDate;

    @Column(name = "REQUEST_UPDATE_DATE",nullable = false)
    @NotNull
    @UpdateTimestamp
    private LocalDateTime requestUpdateDate;

}
