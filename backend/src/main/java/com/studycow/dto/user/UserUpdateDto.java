package com.studycow.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 유저 정보 수정 Dto
 * @author 채기훈
 * @since JDK17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private String userEmail;
    private LocalDate userBirthday;
    private String userThumb;
    private String userNickname;
    private int userPublic;
}
