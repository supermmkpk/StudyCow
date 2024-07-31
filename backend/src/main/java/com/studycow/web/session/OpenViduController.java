package com.studycow.web.session;

import java.util.Map;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.openvidu.java.client.Connection;
import io.openvidu.java.client.ConnectionProperties;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import io.openvidu.java.client.SessionProperties;

/**
 * <pre>
 *  OpenVidu 세션 컨트롤러 클래스
 * </pre>
 *
 * @author 박봉균
 * @since JDK17
 */
@Tag(name = "OpenVidu")
@CrossOrigin(origins = "*")
@RequestMapping("/openvidu/session")
@RestController
public class OpenViduController {

    @Value("${OPENVIDU_URL}")
    private String OPENVIDU_URL;

    @Value("${OPENVIDU_SECRET}")
    private String OPENVIDU_SECRET;

    private OpenVidu openvidu;

    @PostConstruct
    public void init() {
        this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    /**
     * 방 입장
     *
     * @param studyRoomId 방 ID이자 세션 ID
     * @param params      Connection properties
     * @return 커넥션 토큰
     */
    @Operation(summary = "방 입장(연결)", description = "방(세션) ID로 세션을 조회하고 없을 시, 세션을 생성합니다.<br>세션이 있는 경우 연결하고 커넥션 토큰을 반환합니다.")
    @PostMapping("/connect/{studyRoomId}")
    public ResponseEntity<String> createConnection(@PathVariable("studyRoomId") Long studyRoomId, @RequestBody(required = false) Map<String, Object> params) {
        try {
            // 세션이 존재하는지 확인
            Session session = openvidu.getActiveSession(studyRoomId.toString());

            // 없을 경우 생성함
            if (session == null) {
                session = initializeSession(studyRoomId.toString());
            }

            // 세션과 연결
            ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
            Connection connection = session.createConnection(properties);

            //토큰 반환
            return new ResponseEntity<>(connection.getToken(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("방 입장 실패 " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 방 세션 생성(초기화)
     *
     * @param
     * @return The Session ID
     */
    private Session initializeSession(String studyRoomId) throws OpenViduJavaClientException, OpenViduHttpException {
        //SessionProperties 빌드
        SessionProperties properties = new SessionProperties.Builder()
                .customSessionId(studyRoomId)
                .build();

        // 세션 생성
        Session session = openvidu.createSession(properties);

        // 생성한 세션 번호와 방 번호가 다른 경우
        if (!session.getSessionId().equals(studyRoomId)) {
            throw new RuntimeException("방 ID번호와 세션 ID번호가 다름");
        }

        return session;
    }

}
