package com.studycow.service.friend;

import com.studycow.dto.FriendDto;
import org.springframework.stereotype.Service;

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

    /** 친구 관계 추가 */
    void saveFriend(Map<String, Integer> friendMap) throws Exception;
}
