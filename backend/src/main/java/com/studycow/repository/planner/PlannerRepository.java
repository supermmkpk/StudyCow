package com.studycow.repository.planner;

import com.studycow.domain.SubjectCode;
import com.studycow.domain.UserSubjectPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlannerRepository extends JpaRepository<UserSubjectPlan,Long> {
    Optional<List<UserSubjectPlan>> findByUserIdAndPlanDate(Long userId, LocalDate planDate);
    Optional<List<UserSubjectPlan>> findByUserIdAndSubCode(Long userId, SubjectCode subCode);
    Optional<UserSubjectPlan> findByUserIdAndPlanId(Long userId, Long planId);
}
