package com.studycow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * <pre>
 *     친구 요청 엔터티 DTO 클래스
 * </pre>
 *
 * @author 박봉균
 * @since JDK17
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FriendRequestDto {
    private int id;
    private int counterpartUserId;
    private int requestStatus;
    private LocalDateTime requestDate;
    private LocalDateTime requestUpdateDate;
}
