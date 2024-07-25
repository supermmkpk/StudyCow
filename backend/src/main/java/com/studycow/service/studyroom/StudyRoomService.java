package com.studycow.service.studyroom;


import com.studycow.dto.studyroom.StudyRoomDto;

/**
 * <pre>
 *      스터디룸 서비스 구현 클래스
 * </pre>
 *
 * @author 박봉균
 * @since JDK17
 */
public interface StudyRoomService {
    /** 스터디룸 생성 */
    void createStudyRoom(StudyRoomDto studyRoomDto) throws Exception;

}
