package com.studycow.web;

import com.studycow.dto.FriendDto;
import com.studycow.service.friend.FriendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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


}
