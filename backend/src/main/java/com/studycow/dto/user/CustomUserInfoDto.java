package com.studycow.dto.user;

import lombok.*;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 유저 정보 Dto
 * <pre>
 *     유저 정보를 전달하기위한 Dto
 * </pre>
 * @author 채기훈
 * @since JDK17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserInfoDto {

    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private int userPublic;
    private String userThumb;
    private int userGradeCode;
    private int userExp;
    private LocalDateTime userJoinDate;
    private LocalDateTime userUpdateDate;
    private String userNickName;

}
