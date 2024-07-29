package com.studycow.web.session;


import com.studycow.dto.SubjectCodeDto;
import com.studycow.dto.score.ScoreDto;
import com.studycow.dto.score.ScoreTargetDto;
import com.studycow.dto.session.SessionDto;
import com.studycow.service.score.ScoreService;
import com.studycow.service.session.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     성적 관리 컨트롤러 클래스
 * </pre>
 * @author 노명환
 * @since JDK17
 */

@Tag(name = "Session", description = "방 내부 세션 활동")
@RestController
@RequestMapping("/session")
@CrossOrigin("*")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @Operation(summary = "방 입장", description="방에 입장하여 세션id를 부여받고 금일 방에서 공부한 시간을 받습니다.")
    @PostMapping("/enter")
    public ResponseEntity<?> enterRoom(@RequestBody Map<String, Object> requestBody) {
        try {
            SessionDto sessionDto = sessionService.enterRoom(requestBody);
            return ResponseEntity.ok(sessionDto);

        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("방 입장 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "방 퇴장", description="방에서 퇴장하여 지금까지 공부한 시간 및 퇴장시간을 갱신합니다.")
    @PatchMapping("/exit")
    public ResponseEntity<?> exitRoom(@RequestBody Map<String, Object> requestBody) {
        try {
            SessionDto sessionDto = sessionService.exitRoom(requestBody);
            return ResponseEntity.ok(sessionDto);

        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("방 입장 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "공부시간 갱신", description="현 세션에서 지금까지 공부한 시간을 갱신합니다.")
    @PatchMapping("/record")
    public ResponseEntity<?> modifyStudyTime(@RequestBody Map<String, Object> requestBody) {
        try {
            sessionService.modifyStudyTime(requestBody);
            return new ResponseEntity<>("시간 갱신 성공", HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("시간 갱신 실패", HttpStatus.BAD_REQUEST);
        }
    }
}
