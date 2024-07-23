package com.studycow.service.friend;

import com.studycow.dto.FriendDto;
import com.studycow.dto.FriendRequestDto;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 *      친구 관리 서비스 인터페이스
 * </pre>
 * @author 박봉균
 * @since JDK17
 */
public interface FriendService {
    /** 친구 맺은 목록 조회 */
    List<FriendDto> listFriends(int userId) throws  Exception;

    /** 보낸 친구 요청 삭제 */
    void deleteFriendRequest(int friendRequestId) throws Exception;

    /** 친구 요청 승인 */
    void acceptFriendRequest(int friendRequestId) throws Exception;

    /** 친구 요청 저장 */
    void saveFriendRequest(Map<String, Integer> friendRequestMap) throws Exception;

    /** 받은 친구 요청 목록 조회 */
    List<FriendRequestDto> listFriendRequestReceived(int userId) throws Exception;

    /** 보낸 친구 요청 목록 조회 */
    List<FriendRequestDto> listFriendRequestSent(int userId) throws Exception;
}
