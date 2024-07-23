package com.studycow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <pre>
 *     친구 엔터티 DTO 클래스
 * </pre>
 *
 * @author 박봉균
 * @since JDK17
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FriendDto {
    private int friendId;
    private LocalDateTime friendDate;
}
