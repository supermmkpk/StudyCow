package com.studycow.dto.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 방 입장 요청 Dto
 * @author 노명환
 * @since JDK17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterRequestDto {
    private String roomId;
}
