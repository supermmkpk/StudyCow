package com.studycow.web.user;

import com.studycow.dto.user.UserInfoDto;
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


}
