package com.studycow.dto.user;

import com.studycow.domain.UserGrade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 회원가입 Dto
 * @author 채기훈
 * @since JDK17
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDto {

    /** 유저 아아디 **/
    @NotNull
    @NotBlank
    private String userEmail;

    /** 유저 비밀번호 **/
    @NotNull
    @NotBlank
    private String userPassword;

    @NotNull
    @NotBlank
    private int userPublic = 0;

    private String userThumb;

    private UserGrade userGrade;

    @NotNull
    private int userExp = 0;

    @NotNull
    private LocalDateTime userJoinDate = LocalDateTime.now(); ;

    @NotNull
    private LocalDateTime userUpdateDate = LocalDateTime.now();

    @NotNull
    @NotBlank
    private String userNickName;

    @NotNull
    @NotBlank
    private LocalDate userBirthday;
}
