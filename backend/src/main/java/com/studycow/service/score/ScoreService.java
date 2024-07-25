package com.studycow.service.score;

import com.studycow.domain.UserScoreTarget;
import com.studycow.dto.ScoreDto;
import com.studycow.dto.ScoreTargetDto;
import jakarta.persistence.PersistenceException;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 *      성적 관리 서비스 인터페이스
 * </pre>
 * @author 노명환
 * @since JDK17
 */
public interface ScoreService {
    /** 유저 과목별 성적 리스트 조회 */
    List<ScoreDto> listScores(int userId, int subCode) throws Exception;

    /** 단일 성적 조회 */
    ScoreDto scoreDetail(Long scoreId) throws Exception;

    /** 유저 성적 입력 */
    void saveScore(Map<String, Object> scoreMap) throws Exception;

    /** 단일 성적 수정 */
    void modifyScore(Map<String, Object> scoreMap) throws Exception;

    /** 단일 성적 삭제 */
    void deleteScore(Long scoreId) throws Exception;

    /** 성적 목표 등록 */
    void saveScoreTarget(Map<String, Object> targetMap) throws Exception;

    /** 성적 목표 조회 */
    List<ScoreTargetDto> targetList(int userId) throws PersistenceException;

    /** 성적 목표 삭제 */
    void deleteTarget(Long scoreId) throws Exception;
}
