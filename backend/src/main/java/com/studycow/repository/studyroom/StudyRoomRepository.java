package com.studycow.repository.studyroom;

import com.studycow.domain.StudyRoom;
import jakarta.persistence.PersistenceException;

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
}
