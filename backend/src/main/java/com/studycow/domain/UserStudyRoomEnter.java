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
 *     회원 스터디룸 입장 기록 도메인 클래스
 * </pre>
 * @author 박봉균
 * @since JDK17
 */

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

    @Column(name = "STUDY_TIME", nullable = false)
    @NotNull
    private int studyTime;

    @Column(name = "STUDY_DATE", nullable = false)
    @NotNull
    private LocalDate studyDate;

    @Column(name = "IN_DATE", nullable = false)
    @NotNull
    private LocalDateTime inDate;

    @Column(name = "OUT_DATE")
    private LocalDateTime outDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID", nullable = false)
    @NotNull
    private StudyRoom studyRoom; //외래키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    @NotNull
    private User user; //외래키
}
