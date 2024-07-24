package com.studycow.web.user;

import com.studycow.config.jwt.JwtUtil;
import com.studycow.dto.user.UserInfoDto;
import com.studycow.dto.user.UserUpdateDto;
import com.studycow.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "회원 관리")
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Operation(summary="회원", description = "회원")
    @GetMapping("me")
    public ResponseEntity<?> getUser(@RequestParam Long id) {

        UserInfoDto userInfoDto = userService.getUserInfo(id);
        if(userInfoDto == null) {
            return new ResponseEntity<>("유저가 존재하지 않습니다",HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(userInfoDto, HttpStatus.OK);
        }
    }

    @Operation(summary = "회원 정보 수정", description = "회원의 기본정보를 수정합니다.")
    @PatchMapping("me")
    public ResponseEntity<?> UpdateUser(@RequestHeader("Authorization") String authorizationHeader,@RequestBody UserUpdateDto userUpdateDto) {
        String token = authorizationHeader.replace("Bearer ", "");
        int currentUserId = jwtUtil.getUserId(token);

        if(currentUserId!=userUpdateDto.getUserId()){
            return new ResponseEntity<>("인가된 사용자가 아닙니다",HttpStatus.UNAUTHORIZED);
        }

        userService.updateUserInfo(userUpdateDto);
        return new ResponseEntity<>("업데이트 성공",HttpStatus.OK);

    }

    @Operation(summary = "회원 별명 검색", description = "작성한 닉네임을 가진 유저를 검색합니다")
    @GetMapping("/nickname")
    public ResponseEntity<?> searchNickname(@RequestParam String nickname) {
        UserInfoDto userInfoDto = userService.getUserInfoByNickName(nickname);

        return new ResponseEntity<>(userInfoDto,HttpStatus.OK);
    }
}
