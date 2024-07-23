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
 *     스터디룸 도메인 클래스
 * </pre>
 * @author 박봉균
 * @since JDK17
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "t_room", indexes =
@Index(name = "idx_title_status", columnList = "room_title, room_status"))
public class StudyRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long id;

    @Column(name = "ROOM_TITLE", length = 50, nullable = false)
    @NotNull
    private String roomTitle;

    @Column(name = "ROOM_MAX_PERSON", nullable = false)
    @NotNull
    private int roomMaxPerson;

    @Column(name = "ROOM_NOW_PERSON", nullable = false)
    @NotNull
    private int roomNowPerson;

    @Column(name = "ROOM_CREATE_DATE", nullable = false)
    @NotNull
    private LocalDate roomCreateDate;

    @Column(name = "ROOM_END_DATE", nullable = false)
    @NotNull
    private LocalDate roomEndDate;

    @Column(name = "ROOM_STATUS", nullable = false)
    @NotNull
    private int roomStatus;

    @Column(name = "ROOM_UPDATE_DATE", nullable = false)
    @NotNull
    private LocalDateTime roomUpdateDate;

    @Column(name = "ROOM_CONTENT", length = 2000)
    private String roomContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    @NotNull
    private User user; //외래키
}
