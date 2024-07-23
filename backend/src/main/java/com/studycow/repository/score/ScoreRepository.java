package com.studycow.repository.score;


import com.studycow.dto.ScoreDto;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /** 유저 성적 입력 */
    void saveScore(ScoreDto scoreDto) throws PersistenceException;
}
