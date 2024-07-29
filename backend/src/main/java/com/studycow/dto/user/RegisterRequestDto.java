package com.studycow.dto.user;

import com.studycow.domain.UserGrade;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 회원가입 요청 Dto
 * @author 채기훈
 * @since JDK17
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDto {

    /** 이메일, 공백, null 검증  **/
    @NotNull
    @NotBlank(message = "아이디는 필수입니다.")
    @Email
    private String userEmail;

    /** 유저 패스워드 **/
    @NotNull
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String userPassword;

    /** 유저정보 공개여부, 기본값 비허용 **/
    @NotNull
    @Schema(hidden = true)
    private int userPublic = 0;

    /** 유저 썸네일 링크 **/
    @Schema(hidden = true)
    private String userThumb;

    /** 유저 현재 등급 정보, 기본값 1 **/
    @Schema(hidden = true)
    private UserGrade userGrade;

    /** 유저 현재 경험치, 기본값 0 **/
    @NotNull
    @Schema(hidden = true)
    private int userExp = 0;

    /** 유저 회원가입 시간 **/
    @NotNull
    @Schema(hidden = true)
    private LocalDateTime userJoinDate = LocalDateTime.now(); ;

    /** 회원정보 수정 날짜 **/
    @NotNull
    @Schema(hidden = true)
    private LocalDateTime userUpdateDate = LocalDateTime.now();

    /** 유저 닉네임,중복 불가 **/
    @NotNull
    @NotBlank(message = "닉네임은 필수입니다.")
    @Size(min = 2, max = 20 ,message = "닉네임은 2글자 이상, 20글자 이하입니다.")
    private String userNickName;

}
