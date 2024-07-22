package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_in_log")
public class UserStudyRoomEnter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IN_LOG_ID")
    private Long id;

    @Column(name = "STUDY_TIME")
    @NotNull
    private int studyTime;

    @Column(name = "STUDY_DATE")
    @NotNull
    private String studyDate;

    @Column(name = "IN_DATE")
    @NotNull
    private String inDate;

    @Column(name = "OUT_DATE")
    private String outDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    @NotNull
    private StudyRoom studyRoom; //외래키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user; //외래키
}
