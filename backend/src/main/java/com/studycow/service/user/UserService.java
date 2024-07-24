package com.studycow.service.user;


import com.studycow.dto.user.LoginRequestDto;
import com.studycow.dto.user.RegisterRequestDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    String login(LoginRequestDto loginRequestDto);
    void register(RegisterRequestDto signUpRequestDto);

}
