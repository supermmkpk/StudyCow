package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * <pre>
 *     회원 스터디룸 정산 도메인 클래스
 * </pre>
 * @author 박봉균
 * @since JDK17
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "t_proc_room_study", uniqueConstraints =
@UniqueConstraint(name = "uni_room_date", columnNames = {"room_id", "proc_date"}))
public class UserStudyRoomCalculate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROC_ROOM_ID")
    private Long id;

    @Column(name = "PROC_DATE", nullable = false)
    @NotNull
    private LocalDate procDate;

    @Column(name = "SUM_ROOM_TIME", nullable = false)
    @NotNull
    private String sumRoomTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID", nullable = false)
    @NotNull
    private StudyRoom studyRoom; //외래키
}
