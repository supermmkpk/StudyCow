package com.studycow.repository.planner;

import com.studycow.domain.SubjectCode;
import com.studycow.domain.UserSubjectPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * 플래너 Repository
 * <pre>
 *     플래너 저장을 위한 Spring Data JPA
 * </pre>
 * @author 채기훈
 * @since JDK17
 */
public interface PlannerRepository extends JpaRepository<UserSubjectPlan,Long> {
    Optional<List<UserSubjectPlan>> findByUserIdAndPlanDate(Long userId, LocalDate planDate);
    Optional<List<UserSubjectPlan>> findByUserIdAndSubCode(Long userId, SubjectCode subCode);
    Optional<UserSubjectPlan> findByUserIdAndPlanId(Long userId, Long planId);
}
