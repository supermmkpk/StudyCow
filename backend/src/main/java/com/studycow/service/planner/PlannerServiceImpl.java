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
        List<UserSubjectPlan> plans = plannerRepository.findByUserIdAndPlanDate((long)userId,localDate)
                .orElseThrow(()->new EntityNotFoundException("해당하는 플래너 또는 유저가 없습니다."));
        return convertToDtoList(plans);
    }

    @Override
    public List<PlannerGetDto> getPlansBySubjectForUser(int userId, int subjectId) {
        SubjectCode subjectCode = subjectCodeRepository.findById(subjectId)
                .orElseThrow(()->new EntityNotFoundException("과목 코드가 없습니다"));

        List<UserSubjectPlan> plans = plannerRepository.findByUserIdAndSubCode((long)userId,subjectCode)
                .orElseThrow(()->new EntityNotFoundException("해당 과목코드나 유저가 존재하지 않습니다."));
        return convertToDtoList(plans);
    }

    @Override
    public PlannerGetDto getPlanByIdForUser(int userId, int planId) {
        UserSubjectPlan plan = plannerRepository.findByUserIdAndPlanId((long)userId,(long)planId)
                .orElseThrow(()->new EntityNotFoundException("해당하는 플래너 또는 유저가 존재하지 않습니다."));
        PlannerGetDto currentPlan = modelMapper.map(plan, PlannerGetDto.class);

        return currentPlan;
    }

    @Override
    public void updatePlan(int planId, CustomUserDetails customUser,PlannerCreateDto plannerCreateDto ){
        int userId = customUser.getUser().getUserId();

        UserSubjectPlan plan = plannerRepository.findById((long)planId)
                .orElseThrow(()->new EntityNotFoundException("해당 플래너가 존재하지 않습니다."));

        SubjectCode code = subjectCodeRepository.findById(plannerCreateDto.getSubCode())
                .orElseThrow(()->new EntityNotFoundException("해당 과목 코드가 없습니다"));

        if(userId !=plan.getUser().getId()){
            throw new EntityNotFoundException("자신이 작성한 플래너가 아닙니다!");
        }


        if(plan !=null){
            plan.setSubCode(code);
            plan.setPlanDate(plannerCreateDto.getPlanDate());
            plan.setPlanContent(plannerCreateDto.getPlanContent());
            plan.setPlanStudyTime(plannerCreateDto.getPlanStudyTime());
            plan.setPlanStatus(plannerCreateDto.getPlanStatus());
            plannerRepository.save(plan);
        }

    }


    private List<PlannerGetDto> convertToDtoList(List<UserSubjectPlan> plans){
        return plans.stream()
                .map(plan -> modelMapper.map(plan, PlannerGetDto.class))
                .collect(Collectors.toList());
    }
}
