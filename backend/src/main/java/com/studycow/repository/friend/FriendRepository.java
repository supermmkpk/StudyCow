package com.studycow.repository.friend;

import com.studycow.domain.FriendRequest;
import com.studycow.domain.User;
import com.studycow.dto.FriendDto;
import jakarta.persistence.PersistenceException;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 *      친구 관리 레포지토리 인터페이스
 * </pre>
 * @author 박봉균
 * @since JDK17
 */
public interface FriendRepository {
    /** 친구 맺은 목록 조회 */
    List<FriendDto> listFriends(int userId) throws PersistenceException;

    /** 친구 요청 삭제 */
    void deleteFriendRequest(int friendRequestId) throws PersistenceException;

    /** 친구 관계 승인/저장 */
    void acceptFriendRequest(int friendRequestId) throws PersistenceException;

    /** 친구 요청 전송 */
    void saveFriendRequest(int fromUserId, int toUserId) throws PersistenceException;

    /** 받은 친구 요청 목록 조회 */
    List<FriendRequest> listFriendRequestReceived(int userId) throws PersistenceException;

    /** 보낸 친구 요청 목록 조회 */
    List<FriendRequest> listFriendRequestSent(int userId) throws PersistenceException;

    /** 친구 삭제 */
    void deleteFriend(int friendUserId, int userId) throws PersistenceException;
}
