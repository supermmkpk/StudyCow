package com.studycow.repository.score;


import com.studycow.dto.SubjectCodeDto;
import com.studycow.dto.score.ScoreDetailDto;
import com.studycow.dto.score.ScoreDto;
import com.studycow.dto.score.ScoreTargetDto;
import jakarta.persistence.PersistenceException;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 *      성적 관리 레포지토리 인터페이스
 * </pre>
 * @author 노명환
 * @since JDK17
 */

public interface ScoreRepository {
    /** 유저 과목별 성적 리스트 조회 */
    List<ScoreDto> listScores(int userId, int subCode) throws PersistenceException;

    /** 단일 성적 상세 조회 */
    ScoreDto scoreDetail(Long scoreId) throws PersistenceException;

    /** 등록 성적 별 상세 성적 조회 */
    List<ScoreDetailDto> listScoreDetails(Long scoreId) throws PersistenceException;

    /** 유저 성적 입력 */
    Long saveScore(Map<String, Object> scoreMap) throws PersistenceException;

    /** 성적 내 상세 오답 내역 입력 */
    void saveScoreDetails(Long scoreId, int catCode, int wrongCnt) throws PersistenceException;

    /** 단일 성적 삭제 */
    void deleteScore(Long scoreId) throws PersistenceException;

    /** 단일 성적 수정 */
    void modifyScore(Map<String, Object> scoreMap) throws PersistenceException;

    /** 성적 목표 등록 */
    void saveScoreTarget(Map<String, Object> targetMap) throws PersistenceException;

    /** 성적 목표 조회 */
    List<ScoreTargetDto> targetList(int userId) throws PersistenceException;

    /** 성적 목표 삭제 */
    void deleteScoreTarget(Long targetId) throws PersistenceException;

    /** 성적 목표 수정 */
    void modifyScoreTarget(Map<String, Object> targetMap) throws PersistenceException;

    /** 목표 미설정 과목 조회 */
    List<SubjectCodeDto> subjectList(int userId) throws PersistenceException;
}
