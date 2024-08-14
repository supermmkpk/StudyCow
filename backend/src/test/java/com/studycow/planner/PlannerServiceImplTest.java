package com.studycow.planner;

import com.studycow.domain.SubjectCode;
import com.studycow.domain.User;
import com.studycow.domain.UserSubjectPlan;
import com.studycow.dto.plan.PlanCountByDateDto;
import com.studycow.dto.plan.PlannerCreateDto;
import com.studycow.dto.plan.PlannerGetDto;
import com.studycow.dto.user.CustomUserDetails;
import com.studycow.dto.user.CustomUserInfoDto;
import com.studycow.exception.CustomException;
import com.studycow.repository.planner.PlannerRepository;
import com.studycow.repository.subjectcode.SubjectCodeRepository;
import com.studycow.repository.user.UserRepository;
import com.studycow.service.planner.PlannerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class PlannerServiceImplTest {

    @Mock
    private PlannerRepository plannerRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private SubjectCodeRepository subjectCodeRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PlannerServiceImpl plannerService;

    private PasswordEncoder passwordEncoder;
    private CustomUserInfoDto testUserInfo;
    private User testUser;
    private SubjectCode testSubjectCode;
    private UserSubjectPlan testPlan;
    private CustomUserDetails customUserDetails;
    private PlannerCreateDto plannerCreateDto;

    @BeforeEach
    void setUp() {
        testUserInfo = new CustomUserInfoDto(1, "Test User", "test@example.com", "asdf",1,null,1,0,null,null,"hi");

        testUser = new User();
        testUser.setId(1);
        testUser.setUserNickname("Test User");

        testSubjectCode = new SubjectCode(1, "Test Subject", 100, 1, LocalDateTime.now());

        testPlan = new UserSubjectPlan();
        testPlan.setUser(testUser);
        testPlan.setSubCode(testSubjectCode);
        testPlan.setPlanId(1L);
        testPlan.setPlanDate(LocalDate.now());
        testPlan.setPlanContent("Test Content");
        testPlan.setPlanStudyTime(60);

        customUserDetails = new CustomUserDetails(testUserInfo);

        plannerCreateDto = new PlannerCreateDto(1, LocalDate.now(), "Test Content", 60, 0);
    }

    @Test
    void givenValidPlannerCreateDto_whenCreatePlan_thenPlanIsSaved() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(subjectCodeRepository.findById(1)).thenReturn(Optional.of(testSubjectCode));

        plannerService.createPlan(customUserDetails, plannerCreateDto);

        verify(plannerRepository).save(any(UserSubjectPlan.class));
    }

    @Test
    void givenNonExistentUser_whenCreatePlan_thenThrowCustomException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> plannerService.createPlan(customUserDetails, plannerCreateDto));
    }

    @Test
    void givenExistingUserAndDate_whenGetPlansByDateForUser_thenReturnPlanList() {
        LocalDate testDate = LocalDate.now();
        when(plannerRepository.findByUserIdAndPlanDate(1L, testDate))
                .thenReturn(Optional.of(Arrays.asList(testPlan)));
        when(modelMapper.map(any(), eq(PlannerGetDto.class))).thenReturn(new PlannerGetDto(1, 1, 1, testDate, "Test Content", 60, 0));

        List<PlannerGetDto> result = plannerService.getPlansByDateForUser(1, testDate);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    // ... (다른 테스트 메소드들도 비슷한 방식으로 수정)

    @Test
    void givenExistingPlanAndValidDto_whenUpdatePlan_thenPlanIsUpdated() {
        when(plannerRepository.findById(1L)).thenReturn(Optional.of(testPlan));
        when(subjectCodeRepository.findById(1)).thenReturn(Optional.of(testSubjectCode));

        plannerService.updatePlan(1, customUserDetails, plannerCreateDto);

        verify(plannerRepository).save(any(UserSubjectPlan.class));
    }

    @Test
    void givenExistingPlan_whenDeletePlan_thenPlanIsDeleted() {
        when(plannerRepository.findById(1L)).thenReturn(Optional.of(testPlan));

        plannerService.deletePlan(1, customUserDetails);

        verify(plannerRepository).delete(testPlan);
    }

    @Test
    void givenExistingPlan_whenChangePlanStatus_thenStatusIsChanged() {
        when(plannerRepository.findById(1L)).thenReturn(Optional.of(testPlan));

        plannerService.changePlanStatus(1, customUserDetails);

        verify(plannerRepository).save(testPlan);
    }
}