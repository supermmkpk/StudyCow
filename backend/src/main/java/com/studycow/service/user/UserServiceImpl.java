package com.studycow.service.user;

import com.studycow.config.jwt.JwtUtil;
import com.studycow.domain.User;
import com.studycow.domain.UserGrade;
import com.studycow.dto.user.CustomUserInfoDto;
import com.studycow.dto.user.LoginRequestDto;
import com.studycow.dto.user.RegisterRequestDto;
import com.studycow.dto.user.UserInfoDto;
import com.studycow.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final UserGradeRepository userGradeRepository;

    @Override
    @Transactional
    public String login(LoginRequestDto loginRequestDto){
        String userEmail = loginRequestDto.getUserEmail();
        String userPassword = loginRequestDto.getPassword();
        User user = userRepository.findByUserEmail(userEmail);

        if(user ==null || !passwordEncoder.matches(userPassword, user.getUserPassword())){
            throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        CustomUserInfoDto info = modelMapper.map(user, CustomUserInfoDto.class);

        String accessToken = jwtUtil.createAccessToken(info);
        return accessToken;
    }

    @Transactional
    public void register(RegisterRequestDto signUpRequestDto){

        Optional<UserGrade> optionalUserGrade = userGradeRepository.findById(1);

        if(optionalUserGrade.isPresent()){
            signUpRequestDto.setUserGrade(optionalUserGrade.get());
        }else throw new RuntimeException("존재하지 않는 등급입니다.");

        String password = signUpRequestDto.getUserPassword();

        signUpRequestDto.setUserPassword(passwordEncoder.encode(password));

        User user = modelMapper.map(signUpRequestDto, User.class);
        userRepository.save(user);

    }

    @Transactional
    @Override
    public UserInfoDto getUserInfo(Long userId){
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()){
            return modelMapper.map(user.get(), UserInfoDto.class);
        }

        return null;
    }



}
