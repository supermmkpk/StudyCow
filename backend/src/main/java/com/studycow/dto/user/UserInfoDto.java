package com.studycow.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 유저 정보 Dto
 * <pre>
 *     유저 상세 정보를 담아서 전송하는 Dto 입니다.
 * </pre>
 * @author 채기훈
 * @since JDK17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {

    private int userId;
    private String userName;
    private String userEmail;
    private int userPublic;
    private String userThumb;
    private int userGradeCode;
    private int userExp;
    private LocalDateTime userJoinDate;
    private LocalDateTime userUpdateDate;
    private String userNickName;
    private LocalDate userBirthday;
}
