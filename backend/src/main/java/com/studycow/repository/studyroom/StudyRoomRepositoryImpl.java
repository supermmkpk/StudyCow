package com.studycow.repository.studyroom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.studycow.domain.StudyRoom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


/**
 * <pre>
 *      스터디룸 레포지토리 구현 클래스
 * </pre>
 *
 * @author 박봉균
 * @since JDK17
 */
@Repository
@RequiredArgsConstructor
public class StudyRoomRepositoryImpl implements  StudyRoomRepository {

    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    /**
     * 스터디룸 생성
     *
     * @param studyRoom 스터디룸 엔터티
     */
    @Override
    public void createStudyRoom(StudyRoom studyRoom) throws PersistenceException {
        em.persist(studyRoom);
    }
}
