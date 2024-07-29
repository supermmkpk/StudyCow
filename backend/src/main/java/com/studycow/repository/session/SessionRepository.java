package com.studycow.repository.session;


import com.studycow.dto.SubjectCodeDto;
import com.studycow.dto.score.ScoreDetailDto;
import com.studycow.dto.score.ScoreDto;
import com.studycow.dto.score.ScoreTargetDto;
import com.studycow.dto.session.SessionDto;
import jakarta.persistence.PersistenceException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *      방 세션 관련 레포지토리 인터페이스
 * </pre>
 * @author 노명환
 * @since JDK17
 */

public interface SessionRepository {
    /** 방 입장 시 log 입력 */
    SessionDto enterRoom(Map<String, Object> enterMap) throws PersistenceException;

    /** 방 퇴장 시 log 업데이트 */
    SessionDto exitRoom(Map<String, Object> enterMap) throws PersistenceException;

    /** 해당 방에서 금일 공부한 시간 조회 */
    Integer roomStudyTime(int userId, Long roomId, LocalDate studyDate) throws PersistenceException;

    /** 방 퇴장 시 log 업데이트 */
    void modifyStudyTime(Map<String, Object> enterMap) throws PersistenceException;
}
