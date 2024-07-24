package com.studycow.dto.user;

import lombok.*;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

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
    private Date userBirthday;

}
