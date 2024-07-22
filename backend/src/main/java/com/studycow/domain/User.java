package com.studycow.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
@Table(name="t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int id;

    @Column(name = "USER_NAME", length = 20)
    @NotNull
    private String userName;

    @Column(name = "USER_EMAIL", length = 30)
    @NotNull
    private String userEmail;

    @Column(name = "USER_PASSWORD", length = 200)
    @NotNull
    private String userPassword;

    @Column(name = "USER_PUBLIC")
    @NotNull
    private int userPublic;

    @Column(name = "USER_THUMB", length = 100)
    private String userThumb;

    @Column(name = "USER_EXP")
    @NotNull
    private int userExp;

    @Column(name = "USER_JOIN_DATE")
//    @CreationTimestamp
    private LocalDateTime userJoinDate;

    @Column(name = "USER_UPDATE_DATE")
    private LocalDateTime userUpdateDate;

    @Column(name = "USER_NICKNAME", length = 20)
    private String userNickname;

    @Column(name = "USER_BIRTHDAY")
    private LocalDate userBirthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GRADE_CODE")
    @NotNull
    private UserGrade userGrade; //외래키
}
