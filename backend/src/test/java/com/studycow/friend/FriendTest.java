package com.studycow.friend;

import com.studycow.StudycowApplication;
import com.studycow.domain.UserGrade;
import com.studycow.dto.friend.FriendDto;
import com.studycow.dto.friend.FriendRequestDto;
import com.studycow.dto.friend.FriendRequestSendRequestDto;
import com.studycow.dto.listoption.ListOptionDto;
import com.studycow.dto.user.CustomUserInfoDto;
import com.studycow.dto.user.RegisterRequestDto;
import com.studycow.repository.friend.FriendRepository;
import com.studycow.service.friend.FriendService;
import com.studycow.service.user.UserGradeRepository;
import com.studycow.service.user.UserService;
import com.studycow.web.friend.FriendController;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FriendTest {
    @Autowired
    private FriendController friendController;
    @Autowired
    private FriendService friendService;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private ApplicationContext applicationContext;

    private static UserGradeRepository userGradeRepository;
    private static UserService userService;

    private CustomUserInfoDto customUserInfoDto1;
    private CustomUserInfoDto customUserInfoDto2;

    @BeforeAll
    static void setUp() {
        // ApplicationContext를 통해 UserService 빈을 가져옵니다.
        ApplicationContext context = new AnnotationConfigApplicationContext(StudycowApplication.class);
        userService = context.getBean(UserService.class);
        userGradeRepository = context.getBean(UserGradeRepository);

        // 테스트용 UserGrade 생성
        UserGrade userGrade = new UserGrade(1, "Beginner", 0, 100);
        userGradeRepository.save(userGrade);

        // 회원 검증 위한 회원 생성
        RegisterRequestDto registerRequestDto = new RegisterRequestDto();
        registerRequestDto.setUserEmail("testEmail@mail.com");
        registerRequestDto.setUserPassword("testPass");
        registerRequestDto.setUserNickName("testNick");
        registerRequestDto.setUserPublic(1);
        userService.register(registerRequestDto);

        registerRequestDto = new RegisterRequestDto();
        registerRequestDto.setUserEmail("testEmail2@mail.com");
        registerRequestDto.setUserPassword("testPass2");
        registerRequestDto.setUserNickName("testNick2");
        registerRequestDto.setUserPublic(1);
        userService.register(registerRequestDto);

        customUserInfoDto1 = new CustomUserInfoDto(1, "testName", "testEmail", "testPass", 1, "testThumb", 1, 0, LocalDateTime.now(), LocalDateTime.now(), "testNick");
        customUserInfoDto2 = new CustomUserInfoDto(2, "testName2", "testEmail2", "testPass2", 1, "testThumb2", 1, 0, LocalDateTime.now(), LocalDateTime.now(), "testNick2");
    }

    @Test
    @Order(1)
    void testSendFriendRequest() throws Exception {
        // Given
        int fromUserId = 1;
        int toUserId = 2;
        FriendRequestSendRequestDto requestDto = new FriendRequestSendRequestDto();
        requestDto.setToUserId(toUserId);

        // When
        friendService.saveFriendRequest(fromUserId, toUserId);

        // Then
        List<FriendRequestDto> sentRequests = friendService.listFriendRequestSent(fromUserId, new ListOptionDto());
        assertTrue(sentRequests.stream().anyMatch(request -> request.getCounterpartUserId() == toUserId));
    }

    @Test
    @Order(2)
    void testListFriendRequestSent() throws Exception {
        // Given
        int userId = 1;
        ListOptionDto listOptionDto = new ListOptionDto();
        listOptionDto.setSearchText("test");

        // When
        List<FriendRequestDto> result = friendService.listFriendRequestSent(userId, listOptionDto);

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(request -> request.getCounterpartUserNickname().contains("test")));
    }

    @Test
    @Order(3)
    void testListFriendRequestReceived() throws Exception {
        // Given
        int userId = 2;
        ListOptionDto listOptionDto = new ListOptionDto();
        listOptionDto.setSearchText("test");

        // When
        List<FriendRequestDto> result = friendService.listFriendRequestReceived(userId, listOptionDto);

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(request -> request.getCounterpartUserNickname().contains("test")));
    }

    @Test
    @Order(4)
    void testAcceptFriendRequest() throws Exception {
        // Given
        int friendRequestId = 1;
        int userId = 2;

        // When
        friendService.acceptFriendRequest(friendRequestId, userId);

        // Then
        assertFalse(() -> friendRepository.existsFriendRequestById(friendRequestId));
        List<FriendDto> friends = friendRepository.listFriends(userId, new ListOptionDto());
        assertTrue(friends.stream().anyMatch(friend -> friend.getFriendUserId() == userId));
    }

    @Test
    @Order(5)
    void testListFriends() throws Exception {
        // Given
        int userId = 1;
        ListOptionDto listOptionDto = new ListOptionDto();
        listOptionDto.setSearchText("test");
        listOptionDto.setSortKey("friendDate");
        listOptionDto.setIsDESC(true);

        // When
        List<FriendDto> result = friendService.listFriends(userId, listOptionDto);

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(friendDto -> friendDto.getFriendNickname().contains("test")));
        assertTrue(result.size() > 1);
        assertTrue(result.get(0).getFriendDate().isAfter(result.get(1).getFriendDate()));
    }

    @Test
    @Order(6)
    void testCancelFriend() throws Exception {
        // Given
        int friendUserId = 2;
        int userId = 1;

        // When
        friendService.deleteFriend(friendUserId, userId);

        // Then
        List<FriendDto> friends = friendRepository.listFriends(userId, new ListOptionDto());
        assertTrue(friends.stream().noneMatch(friend -> friend.getFriendUserId() == friendUserId));
    }

    @Test
    @Order(7)
    void testCancelFriendRequest() throws Exception {
        // Given
        friendService.saveFriendRequest(1, 2);
        int friendRequestId = 2;

        // When
        friendService.deleteFriendRequest(friendRequestId);

        // Then
        assertFalse(friendRepository.existsFriendRequestById(friendRequestId));
    }
}
