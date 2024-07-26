package com.studycow.repository.session;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.studycow.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.studycow.domain.QUserScoreTarget.userScoreTarget;
import static com.studycow.domain.QUserStudyRoomEnter.userStudyRoomEnter;

/**
 * <pre>
 *      방 세션 관련 레포지토리 구현
 * </pre>
 * @author 노명환
 * @since JDK17
 */
@Repository
@RequiredArgsConstructor
public class SessionRepositoryImpl implements SessionRepository{

    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    /** 방 입장
     * <pre>
     *      방으로 입장을 시도한다.
     *      trigger : TRG_BEFORE_INSERT_IN_LOG
     * </pre>
     * @param userId : 유저 id
     * @param roomId : 방 id
     * @throws PersistenceException : JPA 표준 예외
     */
    @Override
    public void inviteRoom(int userId, Long roomId) throws PersistenceException {
        try {
            User user = em.find(User.class, userId);
            StudyRoom studyRoom = em.find(StudyRoom.class, roomId);

            UserStudyRoomEnter userStudyRoomEnter = new UserStudyRoomEnter(
                    null,
                    0,
                    LocalDate.now(),
                    LocalDateTime.now(),
                    null,
                    studyRoom,
                    user
            );

            em.persist(userStudyRoomEnter);
        }catch(Exception e){
            throw new PersistenceException("목표 등록 중 에러 발생", e);
        }
    }
}
