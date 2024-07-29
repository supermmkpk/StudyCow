package com.studycow.service.session;

import com.studycow.dto.session.SessionDto;
import com.studycow.repository.session.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService{
    private final SessionRepository sessionRepository;

    @Override
    @Transactional
    public SessionDto enterRoom(Map<String, Object> enterMap) throws Exception {
        /** 방 입장 시 log 입력 */
        SessionDto sessionDto = sessionRepository.enterRoom(enterMap);

        /** 해당 방에서 금일 공부한 시간 조회 */
        sessionDto.setRoomStudyTime(sessionRepository.roomStudyTime(
                sessionDto.getUserId(),
                sessionDto.getRoomId(),
                sessionDto.getStudyDate()
        ));
        return sessionDto;
    }

    @Override
    @Transactional
    public SessionDto exitRoom(Map<String, Object> enterMap) throws Exception {
        /** 방 퇴장 시 log 갱신 */
        SessionDto sessionDto = sessionRepository.exitRoom(enterMap);

        /** 해당 방에서 금일 공부한 시간 조회 */
        sessionDto.setRoomStudyTime(sessionRepository.roomStudyTime(
                sessionDto.getUserId(),
                sessionDto.getRoomId(),
                sessionDto.getStudyDate()
        ));
        return sessionDto;
    }

    @Override
    @Transactional
    public void modifyStudyTime(Map<String, Object> enterMap) throws Exception {
        /** 세션 공부시간 갱신 */
        sessionRepository.modifyStudyTime(enterMap);
    }
}
