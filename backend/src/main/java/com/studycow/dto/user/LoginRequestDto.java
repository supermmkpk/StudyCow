package com.studycow.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 로그인 Dto
 * @author 채기훈
 * @since JDK17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    private String userEmail;
    private String password;
}
