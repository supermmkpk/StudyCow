package com.studycow.service.score;

import com.studycow.dto.ScoreDto;
import com.studycow.repository.score.ScoreRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 *      성적 관리 서비스 구현
 * </pre>
 * @author 노명환
 * @since JDK17
 */
@Slf4j
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
    @Transactional
    public void saveScore(Map<String, Object> scoreMap) throws PersistenceException {
        Long scoreId = scoreRepository.saveScore(scoreMap);
        log.info("return scoreId : {}", scoreId);

        Object scoreDetail = scoreMap.get("scoreDetail");

        if(scoreDetail instanceof List<?>) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> scoreDetailList =
                    (List<Map<String, Object>>) scoreMap.get("scoreDetail");

            for (Map<String, Object> detail : scoreDetailList) {
                int catCode = (Integer) detail.get("catCode");
                int wrongCnt = (Integer) detail.get("wrongCnt");
                scoreRepository.saveScoreDetails(scoreId, catCode, wrongCnt);
            }
        }
    }
}
