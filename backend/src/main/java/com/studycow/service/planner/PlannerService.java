package com.studycow.service.planner;

import com.studycow.domain.UserSubjectPlan;
import com.studycow.dto.plan.PlannerCreateDto;
import com.studycow.dto.plan.PlannerGetDto;
import com.studycow.dto.user.CustomUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface PlannerService  {
    void createPlan(CustomUserDetails customUserDetails, PlannerCreateDto plannerCreateDto);
    List<PlannerGetDto> getPlansByDateForUser(int userId, LocalDate localDate);
    List<PlannerGetDto> getPlansBySubjectForUser(int userId, int subjectId);
    PlannerGetDto getPlanByIdForUser(int userId, int planId);
    void updatePlan(int planId, CustomUserDetails customUser, PlannerCreateDto plannerCreateDto );
    void deletePlan(int planId, CustomUserDetails customUser);
}
