package com.studycow.service.user;


import com.studycow.dto.plan.PlannerCreateDto;
import com.studycow.dto.user.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);
    SignUpResponseDto register(RegisterRequestDto signUpRequestDto);
    UserInfoDto getUserInfo(Long userId);
    void updateUserInfo(UserUpdateDto userUpdateDto, CustomUserDetails customUserDetails);
    List<UserInfoDto> getUserInfoByNickName(String nickName);

}
