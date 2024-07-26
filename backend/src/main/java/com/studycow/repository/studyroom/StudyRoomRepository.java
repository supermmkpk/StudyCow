package com.studycow.repository.studyroom;

import com.studycow.domain.StudyRoom;
import com.studycow.dto.studyroom.StudyRoomDto;
import jakarta.persistence.PersistenceException;

import java.util.List;

/**
 * <pre>
 *      스터디룸 레포지토리 인터페이스
 * </pre>
 *
 * @author 박봉균
 * @since JDK17
 */
public interface StudyRoomRepository {
    /** 스터디룸 생성 */
    void createStudyRoom(StudyRoom studyRoom) throws PersistenceException;

    /** 스터디룸 상세 조회 */
    StudyRoomDto getStudyRoomInfo(Long studyRoomId) throws PersistenceException;

}
