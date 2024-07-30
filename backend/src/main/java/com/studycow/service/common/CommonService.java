package com.studycow.service.common;

import com.studycow.dto.common.CategoryCodeDto;
import com.studycow.dto.common.SubjectCodeDto;

import java.util.List;

/**
 * <pre>
 *      공통 서비스 인터페이스
 * </pre>
 * @author 노명환
 * @since JDK17
 */
public interface CommonService {
    /** 과목 리스트 조회 */
    List<SubjectCodeDto> viewSubject() throws Exception;
    /** 문제 유형 리스트 조회 */
    List<CategoryCodeDto> viewCategory(int subCode) throws Exception;
}
