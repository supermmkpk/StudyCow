package com.studycow.repository.score;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.studycow.domain.*;
import com.studycow.dto.ScoreDetailDto;
import com.studycow.dto.ScoreDto;
import com.studycow.dto.ScoreTargetDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.studycow.domain.QFriendRequest.friendRequest;
import static com.studycow.domain.QSubjectCode.subjectCode;
import static com.studycow.domain.QUser.user;
import static com.studycow.domain.QUserScoreTarget.userScoreTarget;
import static com.studycow.domain.QUserSubjectScore.userSubjectScore;
import static com.studycow.domain.QWrongProblem.wrongProblem;


/**
 * <pre>
 *      성적 관리 레포지토리 구현
 * </pre>
 * @author 노명환
 * @since JDK17
 */
@Repository
@RequiredArgsConstructor
public class ScoreRepositoryImpl implements ScoreRepository{

    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

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
        jpql.append("us.id                  as scoreId, \n");
        jpql.append("us.subjectCode.name    as subName, \n");
        jpql.append("us.subjectCode.code    as subCode, \n");
        jpql.append("us.testScore           as testScore, \n");
        jpql.append("us.testGrade           as testGrade, \n");
        jpql.append("us.testDate            as testDate, \n");
        jpql.append("us.updateDate          as scoreUpdateDate) \n");
        jpql.append("FROM UserSubjectScore us\n");
        jpql.append("WHERE us.user.id = :userId \n");
        jpql.append("AND us.subjectCode.code = :subCode \n");

        return em.createQuery(jpql.toString(), ScoreDto.class)
                .setParameter("userId", userId).setParameter("subCode", subCode)
                .getResultList();
    }

    /** 단일 성적 조회
     * <pre>
     *      List로부터 받아온 ID를 기반으로
     *      해당 성적을 조회한다. (수정 페이지에 사용)
     * </pre>
     * @param scoreId : 성적 ID
     * @return : ScoreDto
     * @throws PersistenceException : JPA 표준 예외
     */

    @Override
    public ScoreDto scoreDetail(Long scoreId) throws PersistenceException {
        UserSubjectScore us = em.find(UserSubjectScore.class, scoreId);

        return new ScoreDto(
                us.getId(),
                us.getSubjectCode().getCode(),
                us.getSubjectCode().getName(),
                us.getTestScore(),
                us.getTestGrade(),
                us.getTestDate(),
                us.getUpdateDate(),
                null
        );

        //return scoreDto;
    }

    /** 등록한 성적 상세 조회
     * <pre>
     *      성적 ID를 기반으로 성적의 상세 설명을 조회한다
     * </pre>
     * @param scoreId : 성적 ID
     * @return : ScoreDetailDto 리스트
     * @throws PersistenceException : JPA 표준 예외
     */
    @Override
    public List<ScoreDetailDto> listScoreDetails(Long scoreId) throws PersistenceException {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT new com.studycow.dto.ScoreDetailDto( \n");
        jpql.append("wp.id                      as wrongDetailId, \n");
        jpql.append("wp.userSubjectScore.id     as scoreId, \n");
        jpql.append("wp.problemCategory.id      as catCode, \n");
        jpql.append("wp.problemCategory.name    as catName, \n");
        jpql.append("wp.wrongCount              as wrongCnt) \n");
        jpql.append("FROM WrongProblem wp\n");
        jpql.append("WHERE wp.userSubjectScore.id = :scoreId \n");

        return em.createQuery(jpql.toString(), ScoreDetailDto.class)
                .setParameter("scoreId", scoreId)
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
            SubjectCode subjectCode = em.find(SubjectCode.class, (Integer)scoreMap.get("subCode"));
            LocalDate testDate = LocalDate.parse((String)scoreMap.get("testDate"));
            int testScore = (Integer)scoreMap.get("testScore");
            int testGrade = (Integer)scoreMap.get("testGrade");
            LocalDateTime updateDate = LocalDateTime.now();

            UserSubjectScore userSubjectScore = new UserSubjectScore(
                    null, user, subjectCode, testDate, testScore, testGrade, updateDate
            );

            em.persist(userSubjectScore);
            em.flush();

            return userSubjectScore.getId();
        }catch(Exception e){
            throw new PersistenceException("성적 등록 중 에러 발생", e);
        }
    }

    /** 성적 상세 등록
     * <pre>
     *      페이지에서 입력한 성적을 등록한다
     * </pre>
     * @param scoreId : 성적 id
     * @param catCode : 오답 문제 유형 코드
     * @param wrongCnt : 오답 유형 개수
     * @throws PersistenceException : JPA 표준 예외
     */
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

    /** 단일 성적 삭제
     * <pre>
     *      선택한 성적을 삭제한다. 등록된 오답 유형 또한 삭제된다.
     * </pre>
     * @param scoreId : 성적 id
     * @throws PersistenceException : JPA 표준 예외
     */
    @Override
    public void deleteScore(Long scoreId) throws PersistenceException {
        try{
            UserSubjectScore us = em.find(UserSubjectScore.class, scoreId);

            if(us != null) {
                StringBuilder jpql = new StringBuilder();
                jpql.append("DELETE FROM WrongProblem wp \n");
                jpql.append("WHERE wp.userSubjectScore.id = :scoreId \n");

                em.createQuery(jpql.toString())
                        .setParameter("scoreId", scoreId)
                        .executeUpdate();

                em.remove(us);
            }else{
                throw new EntityNotFoundException("해당 성적을 찾을 수 없습니다.");
            }
        }catch(EntityNotFoundException e) {
            throw e;
        }catch(Exception e){
            throw new PersistenceException("성적 삭제 중 에러 발생", e);
        }
    }

    /** 단일 성적 수정
     * <pre>
     *      선택한 성적을 수정한다.
     *      등록된 오답 유형은 삭제된 뒤 입력된다.
     *      입력 : saveScoreDetail
     * </pre>
     * @param scoreMap : 수정 성적 정보
     * @throws PersistenceException : JPA 표준 예외
     */
    @Override
    public void modifyScore(Map<String, Object> scoreMap) throws PersistenceException {
        try {

            UserSubjectScore us = em.find(UserSubjectScore.class,
                    Long.parseLong((String)scoreMap.get("scoreId")));
            if(us != null) {
                SubjectCode subjectCode = em.find(SubjectCode.class, (int) scoreMap.get("subCode"));
                LocalDate testDate = LocalDate.parse((String)scoreMap.get("testDate"));
                int testScore = (Integer) scoreMap.get("testScore");
                Integer testGrade = (Integer) scoreMap.get("testGrade");

                queryFactory
                        .delete(wrongProblem)
                        .where(wrongProblem.userSubjectScore.id.eq(us.getId()))
                        .execute();

                queryFactory
                        .update(userSubjectScore)
                        .set(userSubjectScore.subjectCode.code, subjectCode.getCode())
                        .set(userSubjectScore.testDate, testDate)
                        .set(userSubjectScore.testScore, testScore)
                        .set(userSubjectScore.testGrade, testGrade)
                        .set(userSubjectScore.updateDate, LocalDateTime.now())
                        .where(userSubjectScore.id.eq(us.getId()))
                        .execute();
            }else{
                throw new EntityNotFoundException("해당 성적을 찾을 수 없습니다.");
            }
        }catch(EntityNotFoundException e) {
            throw e;
        }catch(Exception e){
            throw new PersistenceException("성적 수정 중 에러 발생", e);
        }
    }

    /** 성적 목표 등록
     * <pre>
     *      페이지에서 입력한 목표 성적을 입력한다
     * </pre>
     * @param targetMap : 목표 성적 정보
     * @throws PersistenceException : JPA 표준 예외
     */
    @Override
    public void saveScoreTarget(Map<String, Object> targetMap) throws PersistenceException {
        try {
            User user = em.find(User.class, (Integer)targetMap.get("userId"));
            SubjectCode subjectCode = em.find(SubjectCode.class, (Integer)targetMap.get("subCode"));
            int targetScore = (Integer)targetMap.get("targetScore");
            int targetGrade = (Integer)targetMap.get("targetGrade");

            UserScoreTarget userScoreTarget = new UserScoreTarget(
                    null, user, subjectCode, targetScore, targetGrade
            );

            em.persist(userScoreTarget);
        }catch(Exception e){
            throw new PersistenceException("목표 등록 중 에러 발생", e);
        }
    }

    /** 성적 목표 리스트 조회
     * <pre>
     *      등록한 성적 목표의 목록을 조회한다
     * </pre>
     * @param userId : 유저 ID
     * @throws PersistenceException : JPA 표준 예외
     */
    @Override
    public List<ScoreTargetDto> targetList(int userId) throws PersistenceException {
        return queryFactory
                .select(Projections.constructor(ScoreTargetDto.class,
                        userScoreTarget.id,
                        userScoreTarget.subjectCode.code,
                        userScoreTarget.subjectCode.name,
                        userScoreTarget.targetScore,
                        userScoreTarget.targetGrade))
                .from(userScoreTarget)
                .where(userScoreTarget.user.id.eq(userId))
                .fetch();
    }

    @Override
    public void deleteScoreTarget(Long targetId) throws PersistenceException {
        queryFactory
                .delete(userScoreTarget)
                .where(userScoreTarget.id.eq(targetId))
                .execute();
    }


}
