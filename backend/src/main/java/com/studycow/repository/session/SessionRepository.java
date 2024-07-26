package com.studycow.repository.session;


import com.studycow.dto.SubjectCodeDto;
import com.studycow.dto.score.ScoreDetailDto;
import com.studycow.dto.score.ScoreDto;
import com.studycow.dto.score.ScoreTargetDto;
import jakarta.persistence.PersistenceException;

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
    void inviteRoom(int userId, Long roomId) throws PersistenceException;

}
