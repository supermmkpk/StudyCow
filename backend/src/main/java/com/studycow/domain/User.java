package com.studycow.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <pre>
 *     회원 도메인 클래스
 * </pre>
 * @author 박봉균
 * @since JDK17
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="t_user", indexes =
@Index(name = "idx_userNickname", columnList = "user_nickname"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID",nullable = false)
    private int id;

    @Column(name = "USER_EMAIL", length = 30, unique = true,nullable = false)
    @NotNull
    private String userEmail;

    @Column(name = "USER_PASSWORD", length = 200,nullable = false)
    @NotNull
    private String userPassword;

    @Column(name = "USER_PUBLIC",nullable = false)
    @NotNull
    private int userPublic;

    @Column(name = "USER_THUMB", length = 100)
    private String userThumb;

    @Column(name = "USER_EXP",nullable = false)
    @NotNull
    @ColumnDefault(value = "0")
    private int userExp;

    @Column(name = "USER_JOIN_DATE",nullable = false)
//    @CreationTimestamp
    private LocalDateTime userJoinDate;

    @Column(name = "USER_UPDATE_DATE",nullable = false)
    private LocalDateTime userUpdateDate;

    @Column(name = "USER_NICKNAME", length = 20, unique = true)
    private String userNickname;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GRADE_CODE",nullable = false)
    @NotNull
    private UserGrade userGrade; //외래키
}
