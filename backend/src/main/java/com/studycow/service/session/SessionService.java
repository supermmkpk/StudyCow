package com.studycow.service.session;

import com.studycow.dto.SubjectCodeDto;
import com.studycow.dto.score.ScoreDto;
import com.studycow.dto.score.ScoreTargetDto;
import com.studycow.dto.session.SessionDto;
import jakarta.persistence.PersistenceException;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 *      방 세션 관련 서비스 인터페이스
 * </pre>
 * @author 노명환
 * @since JDK17
 */
public interface SessionService {
    /** 방 입장 시도 */
    SessionDto enterRoom(Map<String, Object> enterMap) throws Exception;
    /** 방 퇴장 시도 */
    SessionDto exitRoom(Map<String, Object> enterMap) throws Exception;
    /** 공부시간 갱신 */
    public void modifyStudyTime(Map<String, Object> enterMap) throws Exception;
}
