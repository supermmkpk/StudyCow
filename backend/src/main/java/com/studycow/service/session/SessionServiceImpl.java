package com.studycow.service.session;

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
    public Long enterRoom(Map<String, Object> enterMap) throws Exception {
        return sessionRepository.enterRoom(enterMap);
    }
}
