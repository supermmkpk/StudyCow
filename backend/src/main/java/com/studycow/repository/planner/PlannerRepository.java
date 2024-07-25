package com.studycow.repository.planner;

import com.studycow.domain.SubjectCode;
import com.studycow.domain.UserSubjectPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PlannerRepository extends JpaRepository<UserSubjectPlan,Long> {
    List<UserSubjectPlan> findByUserIdAndPlanDate(Long userId, LocalDate planDate);
    List<UserSubjectPlan> findByUserIdAndSubCode(Long userId, SubjectCode subCode);

}
