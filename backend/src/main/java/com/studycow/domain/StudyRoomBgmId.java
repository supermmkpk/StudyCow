package com.studycow.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 *     StudyRoomBgm 다중 기본키 클래스
 * </pre>
 * @author 박봉균
 * @since JDK17
 */

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class StudyRoomBgmId implements Serializable {
    private int id;
    private Long studyRoom;
}
