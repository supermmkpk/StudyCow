package com.studycow.repository.planner;

import com.studycow.domain.UserSubjectPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannerRepository extends JpaRepository<UserSubjectPlan,Long> {

}
