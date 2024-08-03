package com.studycow.service.common;

import com.studycow.dto.common.CategoryCodeDto;
import com.studycow.dto.common.SubjectCodeDto;
import com.studycow.repository.common.CommonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <pre>
 *      공통 서비스 구현
 * </pre>
 * @author 노명환
 * @since JDK17
 */
@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService{
    private final CommonRepository commonRepository;

    /**
     * 과목 리스트 조회
     * @return SubjectCodeDto : 과목 정보 Dto
     * @throws Exception
     */
    @Override
    public List<SubjectCodeDto> viewSubject() throws Exception {
        return commonRepository.viewSubject();
    }

    /**
     * 문제 유형 리스트 조회
     * @return CategoryCodeDto : 문제 유형 정보 Dto
     * @throws Exception
     */
    @Override
    public List<CategoryCodeDto> viewCategory(int subCode) throws Exception {
        return commonRepository.viewCategory(subCode);
    }
}
