package com.studycow.web.studyroom;


import com.studycow.dto.studyroom.StudyRoomDto;
import com.studycow.dto.studyroom.StudyRoomRequestDto;
import com.studycow.dto.user.CustomUserDetails;
import com.studycow.service.studyroom.StudyRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "StudyRoom", description = "스터디룸 CRUD")
@RestController
@RequestMapping("/room")
@CrossOrigin("*")
@RequiredArgsConstructor
public class StudyRoomController {

    private final StudyRoomService studyRoomService;

    @Operation(summary = "스터디룸 생성", description = "스터디룸을 생성하여 저장합니다.")
    @PostMapping("/create")
    public ResponseEntity<?> createStudyRoom(
            @RequestBody @Valid StudyRoomRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        try {
            //토큰으로 회원 정보 가져오기
            int userId = userDetails.getUser().getUserId(); //생성(방장) 회원 번호

            //요청 변수를 StudyRoomDto에 넣기
            StudyRoomDto studyRoomDto = new StudyRoomDto();
            studyRoomDto.setUserId(userId);
            studyRoomDto.setRoomTitle(requestDto.getRoomTitle());
            studyRoomDto.setRoomMaxPerson(requestDto.getRoomMaxPerson());
            studyRoomDto.setRoomEndDate(requestDto.getRoomEndDate());
            studyRoomDto.setRoomStatus(requestDto.getRoomStatus());
            studyRoomDto.setRoomContent(requestDto.getRoomContent());

            //방 생성
            studyRoomService.createStudyRoom(studyRoomDto);

            return new ResponseEntity<>("스터디룸 생성 성공", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("스터디룸 생성 실패 : " + e.getMessage(),  HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "상세 조회", description = "스터디룸의 상세 정보를 조회합니다.")
    @GetMapping("/{studyRoomId}")
    public ResponseEntity<?> getStudyRoomInfo(@PathVariable("studyRoomId") Long studyRoomId) {
        try {
            StudyRoomDto studyRoomDto = studyRoomService.getStudyRoomInfo(studyRoomId);
            return ResponseEntity.ok(studyRoomDto);
        } catch (Exception e) {
            return new ResponseEntity<>("스터디룸 상세 조회 실패 : " + e.getMessage(),  HttpStatus.BAD_REQUEST);
        }
    }

}
