package com.studycow.service.friend;

import com.studycow.dto.FriendDto;
import com.studycow.repository.friend.FriendRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * <pre>
 *      친구 관리 서비스 구현 클래스
 * </pre>
 * @author 박봉균
 * @since JDK17
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final FriendRepository friendRepository;

    /** 친구 맺은 목록 조회
     * @param userId : 내 회원 ID
     * @return : FriendDto 리스트
     * @throws Exception
     */
    @Override
    public List<FriendDto> listFriends(int userId) throws Exception {
        return friendRepository.listFriends(userId);
    }

    /** 친구 관계 추가
     * @param friendMap : userId1, userId2 맵
     * @throws Exception
     */
    @Override
    @Transactional
    public void saveFriend(Map<String, Integer> friendMap) throws Exception {
        friendRepository.saveFriend(friendMap);
    }
}
