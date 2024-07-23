package com.studycow.service.friend;

import com.studycow.domain.FriendRequest;
import com.studycow.dto.FriendDto;
import com.studycow.dto.FriendRequestDto;
import com.studycow.repository.friend.FriendRepository;
import lombok.RequiredArgsConstructor;
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

    /** 친구 요청 승인
     * @param friendRequestId
     */
    @Override
    @Transactional
    public void acceptFriendRequest(int friendRequestId) throws Exception {
        //친구 관계 저장 및 요청 삭제
        friendRepository.acceptFriendRequest(friendRequestId);
    }

    /** 보낸 친구 요청 삭제
     *
     * @param friendRequestId
     */
    @Override
    @Transactional
    public void deleteFriendRequest(int friendRequestId) throws Exception {
        friendRepository.deleteFriendRequest(friendRequestId);
    }

    /** 친구 요청 저장
     * @param friendRequestMap fromUserId, toUserId 맵
     * @throws Exception
     */
    @Override
    @Transactional
    public void saveFriendRequest(Map<String, Integer> friendRequestMap) throws Exception {
        friendRepository.saveFriendRequest(friendRequestMap);
    }

    /** 받은 친구 요청 목록 조회
     * @return FriendRequestDto 리스트
     * @throws Exception
     */
   public List<FriendRequestDto> listFriendRequestReceived(int userId) throws Exception {
       List<FriendRequest> friendRequestList = friendRepository.listFriendRequestReceived(userId);

       return friendRequestList
               .stream()
               .map(friendRequest -> new FriendRequestDto(
                       friendRequest.getId(),
                       friendRequest.getFromUser().getId(),
                       friendRequest.getRequestStatus(),
                       friendRequest.getRequestDate(),
                       friendRequest.getRequestUpdateDate()
               ))
               .toList();
   }

    /** 보낸 친구 요청 목록 조회
     * @return : FriendRequestDto 리스트
     * @throws Exception
     */
   public List<FriendRequestDto> listFriendRequestSent(int userId) throws Exception{
       List<FriendRequest> friendRequestList = friendRepository.listFriendRequestSent(userId);

       return friendRequestList
               .stream()
               .map(friendRequest -> new FriendRequestDto(
                       friendRequest.getId(),
                       friendRequest.getToUser().getId(),
                       friendRequest.getRequestStatus(),
                       friendRequest.getRequestDate(),
                       friendRequest.getRequestUpdateDate()
               ))
               .toList();
   }

}
