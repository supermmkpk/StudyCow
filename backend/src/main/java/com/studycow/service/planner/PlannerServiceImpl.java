package com.studycow.service.planner;

import com.studycow.domain.SubjectCode;
import com.studycow.domain.User;
import com.studycow.domain.UserSubjectPlan;
import com.studycow.dto.plan.PlannerCreateDto;
import com.studycow.dto.plan.PlannerGetDto;
import com.studycow.dto.user.CustomUserDetails;
import com.studycow.repository.planner.PlannerRepository;
import com.studycow.repository.subjectcode.SubjectCodeRepository;
import com.studycow.repository.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PlannerServiceImpl implements PlannerService {

    public final PlannerRepository plannerRepository;
    public final ModelMapper modelMapper;
    public final UserRepository userRepository;
    public final SubjectCodeRepository subjectCodeRepository;

    @Override
    public void createPlan(CustomUserDetails customUserDetails, PlannerCreateDto plannerCreateDto){
        User currentUser = userRepository.findById((long)customUserDetails.getUser().getUserId())
                .orElseThrow(()->new EntityNotFoundException("해당 유저가 없습니다."));

        SubjectCode initSubject = subjectCodeRepository.findById(plannerCreateDto.getSubCode())
                .orElseThrow(()->new EntityNotFoundException("과목 코드가 존재하지 않습니다."));

        UserSubjectPlan userSubjectPlan = new UserSubjectPlan();

        modelMapper.map(plannerCreateDto, userSubjectPlan);

        userSubjectPlan.setUser(currentUser);
        userSubjectPlan.setSubCode(initSubject);

        plannerRepository.save(userSubjectPlan);
    }

    @Override
    public List<PlannerGetDto> getPlansByDateForUser(int userId, LocalDate localDate){
        List<UserSubjectPlan> plans = plannerRepository.findByUserIdAndPlanDate((long)userId,localDate);
        return convertToDtoList(plans);
    }

    @Override
    public List<PlannerGetDto> getPlansBySubjectForUser(int userId, int subjectId) {
        SubjectCode subjectCode = subjectCodeRepository.findById(subjectId)
                .orElseThrow(()->new EntityNotFoundException("과목 코드가 없습니다"));

        List<UserSubjectPlan> plans = plannerRepository.findByUserIdAndSubCode((long)userId,subjectCode);
        return convertToDtoList(plans);
    }

    private List<PlannerGetDto> convertToDtoList(List<UserSubjectPlan> plans){
        return plans.stream()
                .map(plan -> modelMapper.map(plan, PlannerGetDto.class))
                .collect(Collectors.toList());
    }
}
