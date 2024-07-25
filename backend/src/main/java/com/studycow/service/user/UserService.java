package com.studycow.service.user;


import com.studycow.dto.user.*;
import org.springframework.http.ResponseEntity;

public interface UserService {

    String login(LoginRequestDto loginRequestDto);
    void register(RegisterRequestDto signUpRequestDto);
    UserInfoDto getUserInfo(Long userId);
    void updateUserInfo(UserUpdateDto userUpdateDto, CustomUserDetails customUserDetails);
    UserInfoDto getUserInfoByNickName(String nickName);
}
