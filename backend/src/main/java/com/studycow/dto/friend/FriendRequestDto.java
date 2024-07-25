package com.studycow.dto.friend;

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
    /** 친구 요청 번호 */
    private int id;
    /** 상대방 회원 번호 */
    private int counterpartUserId;
    /** 요청 상태 */
    private int requestStatus;
    /** 요청일시 */
    private LocalDateTime requestDate;
    /** 요청 변경 일시 */
    private LocalDateTime requestUpdateDate;
}
