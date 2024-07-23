package com.studycow.repository.friend;

import com.studycow.domain.Friend;
import com.studycow.domain.FriendRequest;
import com.studycow.domain.User;
import com.studycow.dto.FriendDto;
import com.studycow.dto.FriendRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *      친구 관리 레포지토리 구현 클래스
 * </pre>
 * @author 박봉균
 * @since JDK17
 */
@Repository
public class FriendRepositoryImpl implements FriendRepository {

    @PersistenceContext
    EntityManager em;

    /** 친구 맺은 목록 조회
     * <pre>
     *      userId1 또는 userId2와 내 ID가 일치할 때,
     *      내 ID와 일치하지 않는 ID와 friendDate를 FriendDto로 반환
     * </pre>
     * @param userId : 내 회원 ID
     * @return : FriendDto 리스트
     * @throws PersistenceException : JPA 표준 예외
     */
    @Override
    public List<FriendDto> listFriends(int userId) throws PersistenceException {
        //동적 쿼리 생성
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT new com.studycow.dto.FriendDto( \n");
        jpql.append("   CASE \n");
        jpql.append("       WHEN f.user1.id = :userId THEN f.user2.id \n");
        jpql.append("       ELSE f.user1.id \n");
        jpql.append("   END AS friendId, f.friendDate) \n");
        jpql.append("FROM Friend f\n");
        jpql.append("WHERE f.user1.id = :userId OR f.user2.id = :userId");

        //FriendDto 리스트 반환
        return em.createQuery(jpql.toString(), FriendDto.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    /** 친구 관계 저장
     * @param friendMap
     * @throws PersistenceException
     */
    @Override
    public void saveFriend(Map<String, Integer> friendMap) throws PersistenceException {
        User user1 = em.find(User.class, friendMap.get("userId1"));
        User user2 = em.find(User.class, friendMap.get("userId2"));

        //@CreationTimestamp이므로 friendDate null
        Friend friend = new Friend(user1, user2, null);
        em.persist(friend);
    }


    /** 친구 요청 저장
     * @param friendRequestMap
     * @throws PersistenceException
     */
    @Override
    public void saveFriendRequest(Map<String, Integer> friendRequestMap) throws PersistenceException {
        User fromUser = em.find(User.class, friendRequestMap.get("fromUserId"));
        User toUser = em.find(User.class, friendRequestMap.get("toUserId"));

        FriendRequest friendRequest = new FriendRequest(fromUser, toUser);
        em.persist(friendRequest);
    }

    /** 받은 친구 요청 목록 조회
     * @return : FriendRequest 리스트
     * @throws PersistenceException
     */
    @Override
    public List<FriendRequest> listFriendRequestReceived(int userId) throws PersistenceException {
        return em.createQuery("SELECT fr FROM FriendRequest fr WHERE fr.toUser.id = :userId ", FriendRequest.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    /** 보낸 친구 요청 목록 조회
     * @return : FriendRequest 리스트
     * @throws PersistenceException
     */
    @Override
    public List<FriendRequest> listFriendRequestSent(int userId) throws PersistenceException {
        return em.createQuery("SELECT fr FROM FriendRequest fr WHERE fr.fromUser.id = :userId ", FriendRequest.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
