package com.studycow.web.friend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studycow.domain.User;
import com.studycow.domain.UserGrade;
import com.studycow.dto.friend.FriendDto;
import com.studycow.dto.friend.FriendRequestDto;
import com.studycow.dto.listoption.ListOptionDto;
import com.studycow.dto.user.CustomUserDetails;
import com.studycow.dto.user.CustomUserInfoDto;
import com.studycow.dto.user.RegisterRequestDto;
import com.studycow.service.friend.FriendService;
import com.studycow.service.user.UserService;
import com.studycow.web.FriendController;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.annotations.ColumnDefault;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * <pre>
 *  친구 관리 컨트롤러 테스트 클래스
 * </pre>
 *
 * @author 박봉균
 * @since JDK17
 */
@WebMvcTest(FriendController.class)
public class FriendControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FriendService friendService;

    @Autowired
    private ObjectMapper objectMapper;

    private CustomUserDetails userDetails1;

    private CustomUserDetails userDetails2;


    @BeforeEach
    void setUp() {
        // CustomUserInfoDto 객체 생성
        CustomUserInfoDto customUserInfoDto = new CustomUserInfoDto(
                1,
                "testName",
                "test@example.com",
                "password123",
                1,
                "thumb_url",
                1,
                0,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "testNickname",
                LocalDate.now());

        // CustomUserDetails 객체 생성
        userDetails1 = new CustomUserDetails(customUserInfoDto);

        customUserInfoDto = new CustomUserInfoDto(
                2,
                "testName2",
                "test2@example.com",
                "password1234",
                1,
                "thumb_url2",
                1,
                0,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "testNickname2",
                LocalDate.now());

        // CustomUserDetails 객체 생성
        userDetails2 = new CustomUserDetails(customUserInfoDto);
    }

    /**
     * 친구 요청 전송 테스트
     *
     * @throws Exception
     */
    @Test
    @WithMockUser
    void sendFriendRequest_ShouldReturnOk() throws Exception {
        Map<String, Integer> requestBody = new HashMap<>();
        requestBody.put("toUserId", 2);

        mockMvc.perform(post("/friend/request")
                        .with(user(userDetails1))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(content().string("친구 요청 전송 성공"));
    }

    /**
     * 보낸 친구 요청 목록 조회 테스트
     *
     * @throws Exception
     */
    @Test
    @WithMockUser
    void sentFriendRequests_ShouldReturnRequestList() throws Exception {
        FriendRequestDto requestDto = new FriendRequestDto(1, 2, "testNickname", "thumb_url",0, LocalDateTime.now(), LocalDateTime.now());
        when(friendService.listFriendRequestSent(anyInt(), any(ListOptionDto.class)))
                .thenReturn(Arrays.asList(requestDto));

        mockMvc.perform(get("/friend/request/sent")
                        .with(user(userDetails1))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].counterpartUserId").value(2))
                .andExpect(jsonPath("$[0].counterpartUserNickname").value("testNickname"))
                .andExpect(jsonPath("$[0].counterpartUserThumb").value("thumb_url"));

    }

    /**
     * 받은 친구 요청 목록 조회 테스트
     *
     * @throws Exception
     */
    @Test
    @WithMockUser
    void receivedFriendRequests_ShouldReturnRequestList() throws Exception {
        FriendRequestDto requestDto = new FriendRequestDto(1, 2, "testNickname", "thumb_url", 0,LocalDateTime.now(), LocalDateTime.now());
        when(friendService.listFriendRequestReceived(anyInt(), any(ListOptionDto.class)))
                .thenReturn(Arrays.asList(requestDto));

        mockMvc.perform(get("/friend/request/received")
                        .with(user(userDetails1))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].counterpartUserId").value(2))
                .andExpect(jsonPath("$[0].counterpartUserNickname").value("testNickname"))
                .andExpect(jsonPath("$[0].counterpartUserThumb").value("thumb_url"));

    }

    /**
     * 친구 요청 승인 테스트
     *
     * @throws Exception
     */
    @Test
    @WithMockUser
    void acceptFriend_ShouldReturnOk() throws Exception {
        sendFriendRequest_ShouldReturnOk();

        mockMvc.perform(post("/friend/accept/1")
                        .with(user(userDetails2))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string("친구 요청 승인 성공"));
    }

    /**
     * 친구 목록 조회 테스트
     *
     * @throws Exception
     */
    @Test
    @WithMockUser
    void listFriends_ShouldReturnFriendList() throws Exception {
        FriendDto friendDto = new FriendDto(2, "nick", "email@test.com", "thumb.jpg", LocalDateTime.now());
        when(friendService.listFriends(anyInt(), any(ListOptionDto.class)))
                .thenReturn(Arrays.asList(friendDto));

        mockMvc.perform(get("/friend/list")
                        .with(user(userDetails1))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].friendUserId").value(2))
                .andExpect(jsonPath("$[0].friendNickname").value("nick"));
    }

    /**
     * 친구 해제 테스트
     *
     * @throws Exception
     */
    @Test
    @WithMockUser
    void cancelFriend_ShouldReturnOk() throws Exception {
        sendFriendRequest_ShouldReturnOk();
        mockMvc.perform(delete("/friend/2")
                        .with(user(userDetails1))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string("친구 해제 성공"));
    }


    /**
     * 친구 요청 취소 테스트
     *
     * @throws Exception
     */
    @Test
    @WithMockUser
    void cancelFriendRequest_ShouldReturnOk() throws Exception {
        mockMvc.perform(delete("/friend/request/cancel/1")
                        .with(user(userDetails1))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string("친구 요청 취소 성공"));
    }



}
