package com.studycow.repository.subjectcode;

import com.studycow.domain.SubjectCode;
import org.springframework.data.repository.CrudRepository;

/**
 * 과목 코드 Repository
 * <pre>
 *     Spring Data Jpa를 활용한 과목 코드 저장소
 * </pre>
 * @author 채기훈
 * @since JDK17
 */
public interface SubjectCodeRepository extends CrudRepository<SubjectCode, Integer> {
}
