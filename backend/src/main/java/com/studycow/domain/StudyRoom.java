package com.studycow.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "t_room")
public class StudyRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long id;

    @Column(name = "ROOM_TITLE", length = 50)
    @NotNull
    private String roomTitle;

    @Column(name = "ROOM_MAX_PERSON")
    @NotNull
    private int roomMaxPerson;

    @Column(name = "ROOM_NOW_PERSON")
    @NotNull
    private int roomNowPerson;

    @Column(name = "ROOM_CREATE_DATE")
    @NotNull
    private LocalDate roomCreateDate;

    @Column(name = "ROOM_END_DATE")
    @NotNull
    private LocalDate roomEndDate;

    @Column(name = "ROOM_STATUS")
    @NotNull
    private int roomStatus;

    @Column(name = "ROOM_UPDATE_DATE")
    @NotNull
    private LocalDateTime roomUpdateDate;

    @Column(name = "ROOM_CONTENT", length = 2000)
    private String roomContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user; //외래키
}
