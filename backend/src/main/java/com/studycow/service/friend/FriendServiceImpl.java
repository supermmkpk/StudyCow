package com.studycow.service.friend;

import com.studycow.domain.FriendRequest;
import com.studycow.dto.friend.FriendDto;
import com.studycow.dto.friend.FriendRequestDto;
import com.studycow.dto.listoption.ListOptionDto;
import com.studycow.repository.friend.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <pre>
 *      친구 관리 서비스 구현 클래스
 * </pre>
 *
 * @author 박봉균
 * @since JDK17
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final FriendRepository friendRepository;

    /**
     * 친구 맺은 목록 조회
     *
     * @param userId 내 회원 ID
     * @return FriendDto 리스트
     * @throws Exception
     */
    @Override
    public List<FriendDto> listFriends(int userId, ListOptionDto listOptionDto) throws Exception {
        return friendRepository.listFriends(userId, listOptionDto);
    }

    /**
     * 친구 요청 승인
     *
     * @param friendRequestId 친구 요청 번호
     * @throws Exception
     */
    @Override
    @Transactional
    public void acceptFriendRequest(int friendRequestId) throws Exception {
        //친구 관계 저장 및 요청 삭제
        friendRepository.acceptFriendRequest(friendRequestId);
    }

    /**
     * 보낸 친구 요청 삭제
     *
     * @param friendRequestId 친구 요청 번호
     * @throws Exception
     */
    @Override
    @Transactional
    public void deleteFriendRequest(int friendRequestId) throws Exception {
        friendRepository.deleteFriendRequest(friendRequestId);
    }

    /**
     * 친구 요청 저장
     *
     * @param fromUserId 보내는 회원 번호
     * @param toUserId   받는 회원 번호
     * @throws Exception
     */
    @Override
    @Transactional
    public void saveFriendRequest(int fromUserId, int toUserId) throws Exception {
        friendRepository.saveFriendRequest(fromUserId, toUserId);
    }

    /**
     * 받은 친구 요청 목록 조회
     *
     * @return FriendRequestDto 리스트
     * @throws Exception
     */
    public List<FriendRequestDto> listFriendRequestReceived(int userId, ListOptionDto listOptionDto) throws Exception {
        List<FriendRequest> friendRequestList = friendRepository.listFriendRequestReceived(userId, listOptionDto);

        return friendRequestList
                .stream()
                .map(friendRequest -> new FriendRequestDto(
                        friendRequest.getId(),
                        friendRequest.getFromUser().getId(),
                        friendRequest.getFromUser().getUserNickname(),
                        friendRequest.getFromUser().getUserThumb(),
                        friendRequest.getRequestStatus(),
                        friendRequest.getRequestDate(),
                        friendRequest.getRequestUpdateDate()
                ))
                .toList();
    }

    /**
     * 보낸 친구 요청 목록 조회
     *
     * @return FriendRequestDto 리스트
     * @throws Exception
     */
    public List<FriendRequestDto> listFriendRequestSent(int userId, ListOptionDto listOptionDto) throws Exception {
        List<FriendRequest> friendRequestList = friendRepository.listFriendRequestSent(userId, listOptionDto);

        return friendRequestList
                .stream()
                .map(friendRequest -> new FriendRequestDto(
                        friendRequest.getId(),
                        friendRequest.getToUser().getId(),
                        friendRequest.getToUser().getUserNickname(),
                        friendRequest.getToUser().getUserThumb(),
                        friendRequest.getRequestStatus(),
                        friendRequest.getRequestDate(),
                        friendRequest.getRequestUpdateDate()
                ))
                .toList();
    }

    /**
     * 친구 삭제
     *
     * @param friendUserId
     * @param userId
     * @throws Exception
     */
    @Override
    @Transactional
    public void deleteFriend(int friendUserId, int userId) throws Exception {
        friendRepository.deleteFriend(friendUserId, userId);
    }

}
