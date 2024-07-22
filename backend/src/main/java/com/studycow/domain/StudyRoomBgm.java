package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <pre>
 *     스터디룸 BGM 도메인 클래스
 * </pre>
 * @author 박봉균
 * @since JDK17
 */

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

    @Column(name = "BGM_LINK", length = 500, nullable = false)
    @NotNull
    private String bgmLink;

    @Column(name = "BGM_IN_DATE", nullable = false)
    @NotNull
    private LocalDateTime bgmInDate;

    @Column(name = "BGM_NAME")
    private String bgmName;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private StudyRoom studyRoom; //외래키
}
