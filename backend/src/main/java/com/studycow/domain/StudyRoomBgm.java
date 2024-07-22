package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@IdClass(StudyRoomBgmId.class)
@Table(name = "t_bgm")
public class StudyRoomBgm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BGM_INDEX")
    private int id;

    @Column(name = "BGM_LINK", length = 500)
    @NotNull
    private String bgmLink;

    @Column(name = "BGM_IN_DATE")
    @NotNull
    private String bgmInDate;

    @Column(name = "BGM_NAME")
    private String bgmName;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private StudyRoom studyRoom; //외래키
}
