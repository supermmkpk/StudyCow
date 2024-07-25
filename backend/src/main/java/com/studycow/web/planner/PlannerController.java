package com.studycow.web.planner;

import com.studycow.dto.plan.PlannerCreateDto;
import com.studycow.dto.plan.PlannerGetDto;
import com.studycow.dto.user.CustomUserDetails;
import com.studycow.service.planner.PlannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planner")
@Tag(name = "Planner", description = "플래너 기본 기능")
public class PlannerController {

    private final PlannerService plannerService;


    @Operation(summary = "플래너 생성", description = "해당 유저의 플래너를 추가합니다")
    @PostMapping("create")
    public ResponseEntity<?> createSubjectPlan(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                               PlannerCreateDto plannerCreateDto) {
        try {
            plannerService.createPlan(customUserDetails, plannerCreateDto);
            return new ResponseEntity<>("등록 성공", HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "플래너 일자별 조회", description = "요청한 날짜에 해당하는 플래너들의 목록을 반환합니다 ")
    @GetMapping("list/day")
    public ResponseEntity<?> listDayPlan(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                         @RequestParam
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date) {

        int userId = customUserDetails.getUser().getUserId();

        List<PlannerGetDto> plans = plannerService.getPlansByDateForUser(userId, date);

        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    @Operation(summary = "플래너 과목별 조회", description = "요청한 과목에 해당하는 플래너들의 목록을 반환합니다")
    @GetMapping("list/subject")
    public ResponseEntity<?> listSubjectPlan(@AuthenticationPrincipal CustomUserDetails user,
                                             @RequestParam int subjectId) {
        int userId = user.getUser().getUserId();

        List<PlannerGetDto> plans = plannerService.getPlansBySubjectForUser(userId, subjectId);

        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

}
