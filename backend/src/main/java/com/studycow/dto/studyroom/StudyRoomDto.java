package com.studycow.dto.studyroom;

import com.studycow.domain.StudyRoom;
import com.studycow.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <pre>
 *     스터디룸 DTO 클래스
 * </pre>
 *
 * @author 박봉균
 * @since JDK17
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudyRoomDto {
    /** 스터디룸 id 번호 */
    private Long id;
    /** 스터디룸명 */
    private String roomTitle;
    /** 최대 인원 */
    private int roomMaxPerson;
    /** 현재 인원 */
    private int roomNowPerson;
    /** 생성일자 */
    private LocalDate roomCreateDate;
    /** 스터디 종료 일자 */
    private LocalDate roomEndDate;
    /** 스터디룸 상태 */
    private int roomStatus;
    /** 수정일자 */
    private LocalDateTime roomUpdateDate;
    /** 상세 내용 */
    private String roomContent;
    /** 생성(방장) 회원 id 번호 */
    private int userId; //외래키

    /**
     * 엔터티로 변환하는 함수
     * <pre>
     *      빌더 패턴을 이용하여 DTO를 Entity로 변환
     * </pre>
     *
     * @param user userId로 조회한 User 객체
     * @return StudyRoom 엔터티 객체
     */
    public StudyRoom toEntity(User user) {
        return StudyRoom.builder()
                .roomTitle(this.roomTitle)
                .roomMaxPerson(this.roomMaxPerson)
                .roomNowPerson(this.roomNowPerson)
                .roomCreateDate(this.roomCreateDate)
                .roomEndDate(this.roomEndDate)
                .roomStatus(this.roomStatus)
                .roomUpdateDate(this.roomUpdateDate)
                .roomContent(this.roomContent)
                .user(user) // User 객체를 설정
                .build();
    }
}
