package com.studycow.repository.friend;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.studycow.domain.*;
import com.studycow.dto.friend.FriendDto;
import com.studycow.dto.listoption.ListOptionDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     *  중복을 제거하고 반대편 회원/친구 정보를 FriendDto로 반환
     * </pre>
     *
     * @param userId 내 회원 ID
     * @return FriendDto 리스트
     * @throws PersistenceException : JPA 표준 예외
     */
    @Override
    public List<FriendDto> listFriends(int userId, ListOptionDto option) throws PersistenceException {
        //user2와 같으면 user1 반환
        List<FriendDto> user1List = queryFactory
                .select(Projections.constructor(FriendDto.class,
                        user.id,
                        user.userNickname,
                        user.userEmail,
                        user.userThumb,
                        friend.friendDate)
                )
                .from(friend)
                .join(friend.user1, user)
                .where(friend.user2.id.eq(userId),
                        searchTextContains(option.getSearchText())
                )
                .fetch();

        //user1과 같으면 user2 반환
        List<FriendDto> user2List = queryFactory
                .select(Projections.constructor(FriendDto.class,
                        user.id,
                        user.userNickname,
                        user.userEmail,
                        user.userThumb,
                        friend.friendDate)
                )
                .from(friend)
                .join(friend.user2, user)
                .where(friend.user1.id.eq(userId),
                        searchTextContains(option.getSearchText())
                )
                .fetch();

        //합치기
        user1List.addAll(user2List);

        return user1List;
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
    public List<FriendRequest> listFriendRequestReceived(int userId) throws PersistenceException {
        return queryFactory
                .selectFrom(friendRequest)
                .join(friendRequest.toUser, user)
                .where(user.id.eq(userId))
                .fetch();
    }

    /**
     * 보낸 친구 요청 목록 조회
     *
     * @return FriendRequest 리스트
     * @throws PersistenceException
     */
    @Override
    public List<FriendRequest> listFriendRequestSent(int userId) throws PersistenceException {
        return queryFactory
                .selectFrom(friendRequest)
                .join(friendRequest.fromUser, user)
                .where(user.id.eq(userId))
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


    private BooleanExpression searchTextContains(String searchText) {
        return searchText != null ? user.userNickname.contains(searchText) : null;
    }

}
