package com.studycow.dto.friend;

import lombok.*;

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
    /** 친구 회원 번호 */
    private int friendUserId;
    /** 친구 닉네임 */
    private String friendNickname;
    /** 친구 이메일 */
    private String friendEmail;
    /** 친구 프로필 사진 */
    private String friendThumb;
    /** 친구 시작 일시 */
    private LocalDateTime friendDate;
}
