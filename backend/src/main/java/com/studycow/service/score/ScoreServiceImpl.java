package com.studycow.service.score;

import com.studycow.dto.ScoreDto;
import com.studycow.repository.score.ScoreRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <pre>
 *      성적 관리 서비스 구현
 * </pre>
 * @author 노명환
 * @since JDK17
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService{
    private final ScoreRepository scoreRepository;

    @Override
    public List<ScoreDto> listScores(int userId, int subCode) throws PersistenceException {
        return scoreRepository.listScores(userId, subCode);
    }

    @Override
    public void saveScore(ScoreDto scoreDto) throws PersistenceException {

    }
}
