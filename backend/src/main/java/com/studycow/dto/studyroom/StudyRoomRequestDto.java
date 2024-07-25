package com.studycow.dto.studyroom;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


/**
 * 스터디룸 요청 DTO 클래스
 * <pre>
 *     Controller단에서 요청 변수를 받고 검증하는 DTO 클래스
 * </pre>
 *
 * @author 박봉균
 * @since JDK17
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class StudyRoomRequestDto {

    /** 스터디룸명 */
    @NotBlank(message = "스터디룸명은 필수입니다.")
    @Size(min = 2, max = 50, message = "스터디룸명은 2자 이상 50자 이하여야 합니다.")
    private String roomTitle;

    /** 최대 인원 */
    @NotNull(message = "최대인원은 필수입니다.")
    @Min(value = 1, message = "최대 인원은 1명 이상이어야 합니다.")
    @Max(value = 20, message = "최대 인원은 20명 이하여야 합니다.")
    private int roomMaxPerson;

    /** 스터디 종료 일자 */
    @NotNull(message = "스터디 종료 일자는 필수입니다.")
    @Future(message = "스터디 종료 일자는 오늘 이후여야 합니다.")
    private LocalDate roomEndDate;

    /** 스터디룸 상태 */
    @Min(value = 0, message = "상태는 0 이상이어야 합니다.")
    private int roomStatus;

    /** 상세 내용 */
    @NotBlank(message = "상세 내용은 필수입니다.")
    private String roomContent;

    /** 생성(방장) 회원 id 번호 */
    /* 토큰 사용
    @NotBlank
    private int userId;
    */

}
