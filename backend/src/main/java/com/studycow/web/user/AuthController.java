package com.studycow.web.user;

import com.studycow.dto.user.LoginRequestDto;
import com.studycow.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Login", description = "Jwt 토큰 발급")
public class AuthController {

    private final UserService userService;

    @PostMapping("login")
    @Operation(summary = "로그인 요청", description = "회원 이메일과 비밀번호로 로그인을 요청합니다.")
    public ResponseEntity<String> getMemberProfile(
            @Valid @RequestBody LoginRequestDto requestDto
    ){
        String token = this.userService.login(requestDto);
        return ResponseEntity.ok(token);
    }

    @GetMapping("test")
    public ResponseEntity<String> getMemberTest(){
        return ResponseEntity.ok("Well done");
    }
}
