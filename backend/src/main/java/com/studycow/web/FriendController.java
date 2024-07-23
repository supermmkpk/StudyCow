package com.studycow.web;

import com.studycow.dto.FriendDto;
import com.studycow.dto.FriendRequestDto;
import com.studycow.service.friend.FriendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     친구 관리 컨트롤러 클래스
 * </pre>
 * @author 박봉균
 * @since JDK17
 */

@Tag(name = "Friend", description = "친구 관리")
@RestController
@RequestMapping("/friend")
@CrossOrigin("*")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @Operation(
            summary = "친구 추가",
            description="친구 요청 수락하여 친구 추가합니다. 추후 요청 테이블 삭제와 연동 개발 예정 <br> userId1, userId2 전달")
    @PostMapping("/accept")
    public ResponseEntity<?> acceptFriend(@RequestBody Map<String, Integer> requestBody) {
        try {
            friendService.saveFriend(requestBody);
            return new ResponseEntity<>("친구 요청 수락 성공",HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("친구 요청 수락 실패", HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "친구 목록 조회", description = "나와 맺은 친구 목록을 조회합니다.")
    @GetMapping("/list")
    public ResponseEntity<?> listFriends(@RequestParam("userId") int userId) {
        try {
            List<FriendDto> friendList = friendService.listFriends(userId);
            return ResponseEntity.ok(friendList);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("친구 목록 조회 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "친구 요청 전송", description="친구 요청을 저장합니다.<br>fromUserId, toUserId 전달")
    @PostMapping("/request")
    public ResponseEntity<?> sendFriendRequest(@RequestBody Map<String, Integer> requestBody) {
        try {
            friendService.saveFriendRequest(requestBody);
            return new ResponseEntity<>("친구 요청 전송 성공", HttpStatus.OK);

        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("친구 요청 전송 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "친구 요청 받은 목록", description = "받은 친구 요청 목록을 조회합니다.")
    @GetMapping("/request/received")
    public ResponseEntity<?> receivedFriendRequests(@RequestParam int userId) {
        try {
            List<FriendRequestDto> friendRequestDtoList = friendService.listFriendRequestReceived(userId);
            return ResponseEntity.ok(friendRequestDtoList);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("받은 친구 요청 목록 조회 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "친구 요청 보낸 목록", description = "보낸 친구 요청 목록을 조회합니다.")
    @GetMapping("/request/sent")
    public ResponseEntity<?> sentFriendRequests(@RequestParam int userId) {
        try {
            List<FriendRequestDto> friendRequestDtoList = friendService.listFriendRequestSent(userId);
            return ResponseEntity.ok(friendRequestDtoList);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("보낸 친구 요청 목록 조회 실패", HttpStatus.BAD_REQUEST);
        }
    }

}
