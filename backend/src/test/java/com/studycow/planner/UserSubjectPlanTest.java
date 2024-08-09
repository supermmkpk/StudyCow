package com.studycow.planner;

import com.studycow.domain.SubjectCode;
import com.studycow.domain.User;
import com.studycow.domain.UserSubjectPlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserSubjectPlanTest {

    private UserSubjectPlan userSubjectPlan;
    private User user;
    private SubjectCode subjectCode;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);

        // SubjectCode 객체 생성 (모든 필드를 포함하는 생성자 사용)
        subjectCode = new SubjectCode(1, "테스트 과목", 100, 1, LocalDateTime.now());

        userSubjectPlan = new UserSubjectPlan();
        userSubjectPlan.setUser(user);
        userSubjectPlan.setSubCode(subjectCode);
        userSubjectPlan.setPlanDate(LocalDate.now());
        userSubjectPlan.setPlanContent("테스트 계획 내용");
        userSubjectPlan.setPlanStudyTime(60);
    }

    @Test
    void testUserSubjectPlanCreation() {
        assertNotNull(userSubjectPlan);
        assertEquals(user, userSubjectPlan.getUser());
        assertEquals(subjectCode, userSubjectPlan.getSubCode());
        assertEquals(LocalDate.now(), userSubjectPlan.getPlanDate());
        assertEquals("테스트 계획 내용", userSubjectPlan.getPlanContent());
        assertEquals(60, userSubjectPlan.getPlanStudyTime());
    }

    @Test
    void testSubjectCodeFields() {
        assertEquals(1, userSubjectPlan.getSubCode().getCode());
        assertEquals("테스트 과목", userSubjectPlan.getSubCode().getName());
        assertEquals(100, userSubjectPlan.getSubCode().getMaxScore());
        assertEquals(1, userSubjectPlan.getSubCode().getStatus());
        assertNotNull(userSubjectPlan.getSubCode().getInDate());
    }

    @Test
    void testDefaultValues() {
        assertEquals(0, userSubjectPlan.getPlanStatus());
        assertEquals(0, userSubjectPlan.getPlanSumTime());
    }

    @Test
    void testPrePersist() {
        userSubjectPlan.onCreate();
        assertNotNull(userSubjectPlan.getPlanInDate());
        assertNotNull(userSubjectPlan.getPlanUpdateDate());
        assertTrue(LocalDateTime.now().minusSeconds(1).isBefore(userSubjectPlan.getPlanInDate()));
        assertTrue(LocalDateTime.now().plusSeconds(1).isAfter(userSubjectPlan.getPlanInDate()));
    }

    @Test
    void testPreUpdate() {
        userSubjectPlan.onCreate(); // 초기 날짜 설정
        LocalDateTime initialUpdateDate = userSubjectPlan.getPlanUpdateDate();

        // 업데이트 시간이 다르도록 짧은 시간 대기
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        userSubjectPlan.onUpdate();
        assertNotEquals(initialUpdateDate, userSubjectPlan.getPlanUpdateDate());
        assertTrue(LocalDateTime.now().minusSeconds(1).isBefore(userSubjectPlan.getPlanUpdateDate()));
        assertTrue(LocalDateTime.now().plusSeconds(1).isAfter(userSubjectPlan.getPlanUpdateDate()));
    }
}