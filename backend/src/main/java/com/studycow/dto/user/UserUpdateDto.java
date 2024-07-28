package com.studycow.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "이메일은 필수입니다.")
    private String userEmail;

    private String userThumb;

    @NotNull
    @NotBlank(message = "닉네임은 필수입니다.")
    private String userNickname;

    @NotNull
    @NotBlank(message = "공개 여부는 필수입니다.")
    @Range(min = 0, max = 1)
    private int userPublic;
}
