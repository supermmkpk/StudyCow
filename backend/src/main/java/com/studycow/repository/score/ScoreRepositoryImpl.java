package com.studycow.repository.score;

import com.studycow.domain.*;
import com.studycow.dto.ScoreDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *      성적 관리 레포지토리 구현
 * </pre>
 * @author 노명환
 * @since JDK17
 */
@Repository
public class ScoreRepositoryImpl implements ScoreRepository{

    @PersistenceContext
    EntityManager em;

    /**과목별 등록한 성적 조회
     * <pre>
     *      현재 로그인한 userId, 입력받은 과목코드를 기반으로
     *      등록된 성적들을 불러온다
     * </pre>
     * @param userId : 내 회원 ID
     * @param subCode : 과목 코드
     * @return : ScoreDto 리스트
     * @throws PersistenceException : JPA 표준 예외
     */
    @Override
    public List<ScoreDto> listScores(int userId, int subCode) throws PersistenceException {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT new com.studycow.dto.ScoreDto( \n");
        jpql.append("us.id as scoreId, \n");
        jpql.append("us.subCode.name as subName, \n");
        jpql.append("us.subCode.code as subCode, \n");
        jpql.append("us.testScore as testScore, \n");
        jpql.append("us.testGrade as testGrade, \n");
        jpql.append("us.testDate as testDate, \n");
        jpql.append("us.updateDate as scoreUpdateDate) \n");
        jpql.append("FROM UserSubjectScore us\n");
        jpql.append("JOIN us.subCode \n");
        jpql.append("WHERE us.user.id = :userId AND us.subCode.code = :subCode");

        return em.createQuery(jpql.toString(), ScoreDto.class)
                .setParameter("userId", userId).setParameter("subCode", subCode)
                .getResultList();
    }

    /** 성적 등록
     * <pre>
     *      페이지에서 입력한 성적을 등록한다
     * </pre>
     * @param scoreMap : 등록할 성적 정보
     * @throws PersistenceException : JPA 표준 예외
     */
    @Override
    public Long saveScore(Map<String, Object> scoreMap) throws PersistenceException {
        try {
            User user = em.find(User.class, (Integer)scoreMap.get("userId"));
            SubjectCode subCode = em.find(SubjectCode.class, (Integer)scoreMap.get("subCode"));
            LocalDate testDate = LocalDate.parse((String)scoreMap.get("testDate"));
            int testScore = (Integer)scoreMap.get("testScore");
            int testGrade = (Integer)scoreMap.get("testGrade");
            LocalDateTime updateDate = LocalDateTime.now();

            UserSubjectScore userSubjectScore = new UserSubjectScore(
                    null, user, subCode, testDate, testScore, testGrade, updateDate
            );

            em.persist(userSubjectScore);
            em.flush();

            return userSubjectScore.getId();
        }catch(Exception e){
            throw new PersistenceException("성적 등록 중 에러 발생", e);
        }
    }

    @Override
    public void saveScoreDetails(Long scoreId, int catCode, int wrongCnt) throws PersistenceException{
        try{
            UserSubjectScore uss = em.find(UserSubjectScore.class, scoreId);
            ProblemCategory pc = em.find(ProblemCategory.class, catCode);

            WrongProblem wrongProblem = new WrongProblem(null, uss, pc, wrongCnt);
            em.persist(wrongProblem);
        }catch(Exception e){
            throw new PersistenceException("성적 상세 등록 중 에러 발생", e);
        }
    }
}
