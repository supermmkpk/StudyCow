package com.studycow.repository.friend;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.studycow.domain.*;
import com.studycow.dto.friend.FriendDto;
import com.studycow.dto.listoption.ListOptionDto;
import com.studycow.util.QueryDslUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.studycow.domain.QFriend.friend;
import static com.studycow.domain.QFriendRequest.friendRequest;
import static com.studycow.domain.QUser.user;

/**
 * <pre>
 *      친구 관리 레포지토리 구현 클래스
 * </pre>
 *
 * @author 박봉균
 * @since JDK17
 */
@Repository
@RequiredArgsConstructor
public class FriendRepositoryImpl implements FriendRepository {

    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


    /**
     * 친구 맺은 목록 조회
     * <pre>
     *  userId1 또는 userId2와 내 ID가 일치하는 행에 대하여,
     *  반대편 회원/친구 정보를 FriendDto로 반환
     * </pre>
     *
     * @param userId 내 회원 ID
     * @return FriendDto 리스트
     * @throws PersistenceException : JPA 표준 예외
     */
    @Override
    public List<FriendDto> listFriends(int userId, ListOptionDto option) throws PersistenceException {
        // userId1 또는 userId2와 내 ID가 일치하는 행에 대하여,
        // 반대편 회원/친구 정보를 FriendDto로 반환
        return queryFactory
                .select(
                        Projections.constructor(FriendDto.class,
                                new CaseBuilder()
                                        .when(friend.user1.id.eq(userId))
                                        .then(friend.user2.id)
                                        .otherwise(friend.user1.id),
                                new CaseBuilder()
                                        .when(friend.user1.id.eq(userId))
                                        .then(friend.user2.userNickname)
                                        .otherwise(friend.user1.userNickname),

                                new CaseBuilder()
                                        .when(friend.user1.id.eq(userId))
                                        .then(friend.user2.userEmail)
                                        .otherwise(friend.user1.userEmail),
                                new CaseBuilder()
                                        .when(friend.user1.id.eq(userId))
                                        .then(friend.user2.userThumb)
                                        .otherwise(friend.user1.userThumb),
                                friend.friendDate
                        )
                )
                .from(friend)
                .where(
                        (friend.user1.id.eq(userId).and(nicknameContains(option.getSearchText(), friend.user2)))
                        .or(friend.user2.id.eq(userId).and(nicknameContains(option.getSearchText(), friend.user1)))
                )
                .orderBy(createOrderSpecifier(option, user, Order.DESC, friend, "friendDate"))
                .fetch();
    }

    /**
     * 친구 요청 삭제
     * <pre>
     *  요청 id 번호를 이용하여 삭제
     * </pre>
     *
     * @param friendRequestId 요청 id 번호
     * @throws PersistenceException
     */
    @Override
    public void deleteFriendRequest(int friendRequestId) throws PersistenceException {
        queryFactory
                .delete(friendRequest)
                .where(friendRequest.id.eq(friendRequestId))
                .execute();
    }

    /**
     * 친구 관계 승인/저장 및 요청 삭제
     *
     * @param friendRequestId 요청 id 번호
     * @throws PersistenceException
     */
    @Override
    public void acceptFriendRequest(int friendRequestId) throws PersistenceException {
        FriendRequest friendRequest = em.find(FriendRequest.class, friendRequestId);

        //@CreationTimestamp이므로 friendDate null
        //친구 관계 저장
        Friend friend = new Friend(friendRequest.getFromUser(), friendRequest.getToUser(), null);
        em.persist(friend);

        //친구 요청 삭제
        em.remove(friendRequest);
    }

    /**
     * 친구 요청 저장
     *
     * @param fromUserId 보내는 회원 번호
     * @param toUserId 받는 회원 번호
     * @throws PersistenceException
     */
    @Override
    public void saveFriendRequest(int fromUserId, int toUserId) throws PersistenceException {
        User fromUser = em.find(User.class, fromUserId);
        User toUser = em.find(User.class,toUserId);

        FriendRequest friendRequest = new FriendRequest(fromUser, toUser);
        em.persist(friendRequest);
    }

    /**
     * 받은 친구 요청 목록 조회
     *
     * @return FriendRequest 리스트
     * @throws PersistenceException
     */
    @Override
    public List<FriendRequest> listFriendRequestReceived(int userId, ListOptionDto option) throws PersistenceException {
        return queryFactory
                .selectFrom(friendRequest)
                .join(friendRequest.toUser, user)
                .where(user.id.eq(userId),
                        nicknameContains(option.getSearchText(), user)
                )
                .orderBy(createOrderSpecifier(option, user, Order.DESC, friendRequest, "requestDate"))
                .fetch();
    }

    /**
     * 보낸 친구 요청 목록 조회
     *
     * @return FriendRequest 리스트
     * @throws PersistenceException
     */
    @Override
    public List<FriendRequest> listFriendRequestSent(int userId, ListOptionDto option) throws PersistenceException {
        return queryFactory
                .selectFrom(friendRequest)
                .join(friendRequest.fromUser, user)
                .where(user.id.eq(userId),
                        nicknameContains(option.getSearchText(), user)
                )
                .orderBy(createOrderSpecifier(option, user, Order.DESC, friendRequest, "requestDate"))
                .fetch();
    }

    /**
     * 친구 삭제
     *
     * @param friendUserId
     * @param userId
     */
    @Override
    public void deleteFriend(int friendUserId, int userId) throws PersistenceException {
        StringBuilder jpql = new StringBuilder();

        queryFactory
                .delete(friend)
                .where(friend.user1.id.eq(friendUserId).or(friend.user1.id.eq(userId)),
                        friend.user1.id.eq(friendUserId).or(friend.user1.id.eq(userId))
                )
                .execute();
    }


    /** 검색 동적 쿼리를 위한 BooleanExpression
     * <pre>
     *     searchText가 null이 아니면 LIKE '%searchText%'가 됨
     *     null일 경우, 무시됨
     * </pre>
     *
     * @param searchText 검색어
     * @return BooleanExpression
     */
    private BooleanExpression nicknameContains(String searchText, QUser qUser) {
        return searchText != null ? qUser.userNickname.contains(searchText) : null;
    }

    private OrderSpecifier[] createOrderSpecifier(ListOptionDto option,
                                                  Path<?> parent,
                                                  Order defaultDirection,
                                                  Path<?> defaultParent,
                                                  String defaultFieldName) {
        String sortKey = option.getSortKey();
        String sortDirection = option.getSortDirection();

        List<OrderSpecifier> orderSpecifierList = new ArrayList<>();

        // 정렬 기준이 null이 아니라면
        if(sortKey != null && !sortKey.isBlank()){
            if(sortDirection == null) {
                //정렬 방향이 null이라면 오름차순
                orderSpecifierList.add(QueryDslUtil.getSortedColumn(Order.ASC, parent, sortKey));
            } else {
                orderSpecifierList.add(QueryDslUtil.getSortedColumn(Order.DESC, parent, sortKey));
            }
        } else {
            if(sortDirection == null) {
                //정렬 기준이 null이고 방향도 null이라면
                orderSpecifierList.add(QueryDslUtil.getSortedColumn(defaultDirection, defaultParent, defaultFieldName));
            } else {
                orderSpecifierList.add(QueryDslUtil.getSortedColumn(defaultDirection, defaultParent, defaultFieldName));
            }
        }

        return orderSpecifierList.toArray(new OrderSpecifier[orderSpecifierList.size()]);
    }

}
