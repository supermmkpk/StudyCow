package com.studycow.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

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

    @Email
    @NotNull
    private String userEmail;
    private String userThumb;
    @NotNull
    private String userNickname;
    @NotNull
    @Range(min = 0, max = 1)
    private int userPublic;
}
