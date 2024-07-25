package com.studycow.web;


import com.studycow.dto.ScoreDto;
import com.studycow.dto.ScoreTargetDto;
import com.studycow.dto.user.CustomUserDetails;
import com.studycow.service.score.ScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Operation(summary = "성적 단일 조회", description = "선택한 성적의 상세 정보를 조회합니다.")
    @GetMapping("/detail")
    public ResponseEntity<?> scoreDetail(@RequestParam("scoreId") Long scoreId) {
        try {
            ScoreDto scoreDto = scoreService.scoreDetail(scoreId);
            return ResponseEntity.ok(scoreDto);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("성적 조회 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "성적 등록", description="성적을 등록합니다.<br>필수 : userId, subCode, testDate, testScore" +
            "<br>선택 : testGrade, scoreDetail")
    @PostMapping("/regist")
    public ResponseEntity<?> registScore(@RequestBody Map<String, Object> requestBody) {
        try {
            scoreService.saveScore(requestBody);
            return new ResponseEntity<>("성적 등록 성공", HttpStatus.OK);

        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("성적 등록 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "성적 수정", description="성적을 수정합니다.<br>필수 : scoreId, subCode, testDate, testScore" +
            "<br>선택 : testGrade, scoreDetail")
    @PatchMapping("/modify")
    public ResponseEntity<?> modifyScore(@RequestBody Map<String, Object> requestBody) {
        try {
            scoreService.modifyScore(requestBody);
            return new ResponseEntity<>("성적 수정 성공", HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("성적 수정 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "성적 삭제", description="성적을 삭제합니다.")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteScore(@RequestParam("scoreId") Long scoreId) {
        try {
            scoreService.deleteScore(scoreId);
            return new ResponseEntity<>("성적 삭제 성공", HttpStatus.OK);

        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("성적 삭제 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "목표 등록", description="목표를 등록합니다.")
    @PostMapping("/target/regist")
    public ResponseEntity<?> registTarget(@RequestBody Map<String, Object> requestBody) {
        try {
            scoreService.saveScoreTarget(requestBody);
            return new ResponseEntity<>("목표 등록 성공", HttpStatus.OK);

        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("목표 등록 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "성적 목표 조회", description = "유저의 성적 목표를 조회합니다.")
    @GetMapping("/target")
    public ResponseEntity<?> targetList(@RequestParam("userId") int userId) {
        try {
            List<ScoreTargetDto> scoreTargetDtoList = scoreService.targetList(userId);
            return ResponseEntity.ok(scoreTargetDtoList);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("성적 목표 조회 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "성적 목표 삭제", description="성적 목표를 삭제합니다.")
    @DeleteMapping("/target/delete")
    public ResponseEntity<?> deleteTarget(@RequestParam("targetId") Long targetId) {
        try {
            scoreService.deleteTarget(targetId);
            return new ResponseEntity<>("성적 목표 삭제 성공", HttpStatus.OK);

        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("성적 목표 삭제 실패", HttpStatus.BAD_REQUEST);
        }
    }
}
