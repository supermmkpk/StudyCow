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

    /**
     * 과목별 성적 리스트 조회
     * @param userId : 유저 id
     * @param subCode : 과목 코드
     * @throws Exception
     */
    @Override
    public List<ScoreDto> listScores(int userId, int subCode) throws Exception {
        return scoreRepository.listScores(userId, subCode);
    }

    /**
     * 상세 포함 성적 등록
     * @param scoreMap : 성적 정보(상세 포함)
     * @throws Exception
     */
    @Override
    @Transactional
    public void saveScore(Map<String, Object> scoreMap) throws Exception {
        //성적 등록 후 scoreId를 return
        Long scoreId = scoreRepository.saveScore(scoreMap);
        log.info("return scoreId : {}", scoreId);

        Object scoreDetail = scoreMap.get("scoreDetail");

        //scoreDetail 데이터가 있고 List 형식일 경우
        if(scoreDetail instanceof List<?>) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> scoreDetailList =
                    (List<Map<String, Object>>) scoreMap.get("scoreDetail");

            //반복해서 상세 성적을 등록
            for (Map<String, Object> detail : scoreDetailList) {
                int catCode = (Integer) detail.get("catCode");
                int wrongCnt = (Integer) detail.get("wrongCnt");
                scoreRepository.saveScoreDetails(scoreId, catCode, wrongCnt);
            }
        }
    }
}
