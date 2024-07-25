package com.studycow.service.score;

import com.studycow.dto.ScoreDetailDto;
import com.studycow.dto.ScoreDto;
import com.studycow.dto.ScoreTargetDto;
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
     * 상세 포함 과목별 성적 리스트 조회
     * @param userId : 유저 id
     * @param subCode : 과목 코드
     * @throws Exception
     */
    @Override
    public List<ScoreDto> listScores(int userId, int subCode) throws Exception {
        // 과목별 성적 리스트
        List<ScoreDto> scoreDtoList = scoreRepository.listScores(userId, subCode);

        // 오답 유형 리스트 조회
        for(ScoreDto scores : scoreDtoList){
            Long scoreId = scores.getScoreId();

            List<ScoreDetailDto> scoreDetailDtoList = scoreRepository.listScoreDetails(scoreId);
            if(scoreDetailDtoList != null && !scoreDetailDtoList.isEmpty())
                scores.setScoreDetails(scoreDetailDtoList);
        }

        return scoreDtoList;
    }

    /**
     * 단일 성적 상세 조회
     * @param scoreId : 성적 ID
     * @throws Exception
     */
    @Override
    public ScoreDto scoreDetail(Long scoreId) throws Exception {
        ScoreDto scoreDto = scoreRepository.scoreDetail(scoreId);

        List<ScoreDetailDto> scoreDetailDtoList = scoreRepository.listScoreDetails(scoreId);
        if(scoreDetailDtoList != null && !scoreDetailDtoList.isEmpty())
            scoreDto.setScoreDetails(scoreDetailDtoList);

        return scoreDto;
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

    /**
     * 상세 포함 성적 수정
     * @param scoreMap : 성적 정보(상세 포함)
     * @throws Exception
     */
    @Override
    @Transactional
    public void modifyScore(Map<String, Object> scoreMap) throws Exception {
        Long scoreId = Long.parseLong((String) scoreMap.get("scoreId"));
        scoreRepository.modifyScore(scoreMap);

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

    /**
     * 단일 성적 삭제
     * @param scoreId : 성적 id
     * @throws Exception
     */
    @Override
    @Transactional
    public void deleteScore(Long scoreId) throws Exception {
        scoreRepository.deleteScore(scoreId);
    }

    /**
     * 성적 목표 등록
     * @param targetMap : 성적 목표 정보
     * @throws Exception
     */
    @Override
    @Transactional
    public void saveScoreTarget(Map<String, Object> targetMap) throws Exception {
        scoreRepository.saveScoreTarget(targetMap);
    }

    /**
     * 목표 목록 조회
     * @param userId : 유저 id
     * @throws Exception
     */
    @Override
    public List<ScoreTargetDto> targetList(int userId) throws PersistenceException {
        return scoreRepository.targetList(userId);
    }

    /**
     * 성적 목표 삭제
     * @param targetId : 목표 id
     * @throws Exception
     */
    @Override
    @Transactional
    public void deleteTarget(Long targetId) throws Exception {
        scoreRepository.deleteScoreTarget(targetId);
    }

    /**
     * 성적 목표 수정
     * @param targetMap : 목표 정보
     * @throws Exception
     */
    @Override
    @Transactional
    public void modifyTarget(Map<String, Object> targetMap) throws Exception {
        scoreRepository.modifyScoreTarget(targetMap);
    }

}
