package com.studycow.service.studyroom;

import com.studycow.domain.StudyRoom;
import com.studycow.domain.User;
import com.studycow.dto.studyroom.StudyRoomDto;
import com.studycow.repository.studyroom.StudyRoomRepository;
import com.studycow.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * <pre>
 *      스터디룸 서비스 구현 클래스
 * </pre>
 *
 * @author 박봉균
 * @since JDK17
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyRoomServiceImpl implements StudyRoomService {

    private final StudyRoomRepository studyRoomRepository;
    private final UserRepository userRepository;

    /**
     * 스터디룸 생성
     *
     * @param studyRoomDto 스터디룸 DTO
     * @throws Exception
     */
    @Override
    @Transactional
    public void createStudyRoom(StudyRoomDto studyRoomDto) throws Exception {
        User user = userRepository.findById((long) studyRoomDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자 ID가 존재하지 않습니다."));

        StudyRoom studyRoom = studyRoomDto.toEntity(user);
        studyRoomRepository.createStudyRoom(studyRoom);
    }
}
