package com.studycow.web.user;

import com.studycow.dto.user.LoginRequestDto;
import com.studycow.dto.user.RegisterRequestDto;
import com.studycow.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Auth 관련 서비스 클래스
 * <pre>
 *     사용자 인가 및 인증 관련 클래스 모음입니다
 * </pre>
 * @author 채기훈
 * @since JDK17
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Login", description = "Jwt 토큰 발급")
public class AuthController {

    private final UserService userService;

    /**
     * 로그인
     * @param requestDto
     * @return JwtToken
     */
    @PostMapping("login")
    @Operation(summary = "로그인 요청", description = "회원 이메일과 비밀번호로 로그인을 요청합니다.")
    public ResponseEntity<String> getMemberProfile(
            @Valid @RequestBody LoginRequestDto requestDto
    ){
        String token = this.userService.login(requestDto);

        return ResponseEntity.ok(token);
    }

    /**
     * 인가요청 테스트
     * @return ResponseEntity
     */
    @GetMapping("test")
    public ResponseEntity<String> getMemberTest(){
        return ResponseEntity.ok("Well done");
    }

    /**
     * 회원가입
     * @param requestDto 회원가입 요청 데이터
     * @return ResponseEntity
     */
    @Operation(summary = "회원가입", description = "회원의 기본정보를 받고 가입시켜 줍니다.")
    @PostMapping("register")
    public ResponseEntity<?> registerMember(@Valid @RequestBody RegisterRequestDto requestDto){
        try{
            userService.register(requestDto);
            return new ResponseEntity<>("회원가입 완료", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("회원가입 실패" + e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }



}
