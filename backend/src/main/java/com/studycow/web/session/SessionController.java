package com.studycow.web.session;


import com.studycow.dto.SubjectCodeDto;
import com.studycow.dto.score.ScoreDto;
import com.studycow.dto.score.ScoreTargetDto;
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

    @Operation(summary = "방 입장", description="방에 입장하여 세션id를 부여받습니다.")
    @PostMapping("/enter")
    public ResponseEntity<?> enterRoom(@RequestBody Map<String, Object> requestBody) {
        try {
            Map<String, String> response = new HashMap<>();
            Long sessionId = sessionService.enterRoom(requestBody);
            response.put("sessionId", sessionId.toString());

            return ResponseEntity.ok(response);

        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("성적 등록 실패", HttpStatus.BAD_REQUEST);
        }
    }
}
