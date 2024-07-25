package com.studycow.dto.user;

import lombok.*;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 유저 정보 Dto
 * <pre>
 *     유저 정보 조회를 위한 Dto
 * </pre>
 * @author 채기훈
 * @since JDK17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserInfoDto {
    /** 유저 고유 id**/
    private int userId;
    /** 유저 이름 **/
    private String userName;
    /** 유저 아이디 **/
    private String userEmail;
    /** 유저 비밀번호 **/
    private String userPassword;
    /** 유저 공개 여부 **/
    private int userPublic;
    /** 유저 추천도 **/
    private String userThumb;
    /** 유저 현재 등급 **/
    private int userGradeCode;
    /** 유저 현재 경험치 **/
    private int userExp;
    /** 유저 회원가입 날짜 **/
    private LocalDateTime userJoinDate;
    /** 유저 정보 수정 날짜 **/
    private LocalDateTime userUpdateDate;
    /** 유저 별명 **/
    private String userNickName;
    /** 유저 생일 **/
    private LocalDate userBirthday;

}
