package com.studycow.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Table(name = "t_user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_NAME", length = 20)
    @NotNull
    private String name;

    @Column(name = "USER_EMAIL", length = 30)
    @NotNull
    private String email;

    @Column(name = "USER_PASSWORD", length = 200)
    @NotNull
    private String password;

    @Column(name = "USER_PUBLIC")
    @NotNull
    private Boolean isPublic;

    @Column(name = "USER_THUMB", length = 100)
    private String thumb;

    @JoinColumn(name = "GRADE_CODE")
    @NotNull
    private Integer gradeCode;

    @Column(name = "USER_EXP")
    @NotNull
    private Integer exp;

    @Column(name = "USER_JOIN_DATE")
    @CreationTimestamp
    private LocalDateTime joinDate;

    @Column(name = "USER_UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "USER_NICKNAME", length = 20)
    private String nickname;

    @Column(name = "USER_BIRTHDAY")
    private Date birthday;


}
