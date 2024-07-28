package com.studycow.repository.studyroom;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.studycow.domain.StudyRoom;
import com.studycow.dto.listoption.ListOptionDto;
import com.studycow.dto.studyroom.StudyRoomDto;
import com.studycow.dto.studyroom.StudyRoomRequestDto;
import com.studycow.util.QueryDslUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.studycow.domain.QStudyRoom.*;


/**
 * <pre>
 *      스터디룸 CRUD 레포지토리 구현 클래스
 * </pre>
 *
 * @author 박봉균
 * @since JDK17
 */
@Repository
@RequiredArgsConstructor
public class StudyRoomRepositoryImpl implements StudyRoomRepository {

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

    /**
     * 스터디룸 상세 조회
     *
     * @param studyRoomId 스터디룸 고유 번호
     * @return StudyRoomDto
     * @throws PersistenceException
     */
    @Override
    public StudyRoomDto getStudyRoomInfo(Long studyRoomId) throws PersistenceException {
        return queryFactory
                .select(Projections.constructor(StudyRoomDto.class,
                        studyRoom.id,
                        studyRoom.roomTitle,
                        studyRoom.roomMaxPerson,
                        studyRoom.roomNowPerson,
                        studyRoom.roomCreateDate,
                        studyRoom.roomEndDate,
                        studyRoom.roomStatus,
                        studyRoom.roomUpdateDate,
                        studyRoom.roomContent,
                        studyRoom.user.id
                ))
                .from(studyRoom)
                .where(studyRoom.id.eq(studyRoomId))
                .fetchOne();
    }

    /**
     * 스터디룸 목록 조회
     * <pre>
     *     검색어가 존재할 경우, roomTitle로 검색한다.
     *     정렬 조건이 있을 경우, 그에 따라 정렬한다.
     *     기본 정렬 전략은 studyRoom.id 기준 Order.ASC이다.
     * </pre>
     *
     * @param option 검색/정렬 조건
     * @throws PersistenceException
     */
    @Override
    public List<StudyRoomDto> listStudyRoom(ListOptionDto option) throws PersistenceException {
        return queryFactory
                .select(Projections.constructor(StudyRoomDto.class,
                        studyRoom.id,
                        studyRoom.roomTitle,
                        studyRoom.roomMaxPerson,
                        studyRoom.roomNowPerson,
                        studyRoom.roomCreateDate,
                        studyRoom.roomEndDate,
                        studyRoom.roomStatus,
                        studyRoom.roomUpdateDate,
                        studyRoom.roomContent,
                        studyRoom.user.id
                ))
                .from(studyRoom)
                .where(containsName(option.getSearchText()))
                .orderBy(QueryDslUtil.createOrderSpecifier(option, studyRoom, studyRoom, "id"))
                .fetch();
    }

    /**
     * 스터디룸 수정
     *
     * @param studyRoomId 스터디룸 고유번호
     * @param requestDto  수정 정보를 담은 DTO
     * @param userId      요청 회원 고유번호
     * @throws PersistenceException
     */
    @Override
    public void updateStudyRoom(Long studyRoomId, StudyRoomRequestDto requestDto, int userId) throws PersistenceException {
        StudyRoom studyRoomFound = em.find(StudyRoom.class, studyRoomId);

        if (studyRoomFound.getUser().getId() != userId) {
            throw new IllegalStateException("방장만 수정할 수 있습니다.");
        }

        queryFactory
                .update(studyRoom)
                .set(studyRoom.roomTitle, requestDto.getRoomTitle())
                .set(studyRoom.roomMaxPerson, requestDto.getRoomMaxPerson())
                .set(studyRoom.roomEndDate, requestDto.getRoomEndDate())
                .set(studyRoom.roomStatus, requestDto.getRoomStatus())
                .set(studyRoom.roomContent, requestDto.getRoomContent())
                .where(studyRoom.id.eq(studyRoomId))
                .execute();
    }

    /**
     * 스터디룸명 검색 동적 쿼리
     *
     * @param searchText 검색어
     * @return BooleanExpression
     */
    private BooleanExpression containsName(String searchText) {
        return (searchText != null && !searchText.isBlank())
                ? studyRoom.roomTitle.contains(searchText) : null;
    }

}
