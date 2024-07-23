package com.studycow.web.user;

import com.studycow.dto.user.LoginRequestDto;
import com.studycow.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("login")
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
