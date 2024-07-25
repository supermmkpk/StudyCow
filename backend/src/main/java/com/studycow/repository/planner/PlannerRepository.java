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
 *     Spring Data Jpa를 활용한 플래너 저장소
 * </pre>
 * @author 채기훈
 * @since JDK17
 */
public interface PlannerRepository extends JpaRepository<UserSubjectPlan,Long> {
    /** 유저 정보와 생성일 기준 플래너 조회 **/
    Optional<List<UserSubjectPlan>> findByUserIdAndPlanDate(Long userId, LocalDate planDate);
    /** 유저 정보와 과목 기준 플래너 조회 **/
    Optional<List<UserSubjectPlan>> findByUserIdAndSubCode(Long userId, SubjectCode subCode);
    /** 유저 정보와 플래너 번호 기준 상세 조회 **/
    Optional<UserSubjectPlan> findByUserIdAndPlanId(Long userId, Long planId);
}
