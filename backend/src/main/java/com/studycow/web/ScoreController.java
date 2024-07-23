package com.studycow.web;


import com.studycow.dto.ScoreDto;
import com.studycow.service.score.ScoreService;
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
 *     성적 관리 컨트롤러 클래스
 * </pre>
 * @author 노명환
 * @since JDK17
 */

@Tag(name = "Score", description = "성적 관리")
@RestController
@RequestMapping("/score")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @Operation(summary = "과목별 성적 조회", description = "등록한 과목별 성적을 조회합니다.")
    @GetMapping("/list")
    public ResponseEntity<?> listScore(@RequestParam("userId") int userId,
                                       @RequestParam("subCode") int subCode) {
        try {
            List<ScoreDto> scoreList = scoreService.listScores(userId, subCode);
            return ResponseEntity.ok(scoreList);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("성적 조회 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "성적 등록", description="성적을 등록합니다.<br>필수 : userId, subCode, testDate, testScore" +
            "<br>선택 : testGrade, scoreDetail")
    @PostMapping("/regist")
    public ResponseEntity<?> sendFriendRequest(@RequestBody Map<String, Object> requestBody) {
        try {
            scoreService.saveScore(requestBody);
            return new ResponseEntity<>("성적 등록 성공", HttpStatus.OK);

        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("성적 등록 실패", HttpStatus.BAD_REQUEST);
        }
    }
}
