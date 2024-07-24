package com.studycow.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private int userId;
    private String userEmail;
    private LocalDate userBirthday;
    private String userThumb;
    private String userNickname;
    private int userPublic;
}
