package com.studycow.repository.common;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.studycow.domain.ProblemCategory;
import com.studycow.domain.SubjectCode;
import com.studycow.dto.common.CategoryCodeDto;
import com.studycow.dto.common.SubjectCodeDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.studycow.domain.QProblemCategory.problemCategory;
import static com.studycow.domain.QSubjectCode.subjectCode;

/**
 * <pre>
 *      방 세션 관련 레포지토리 구현
 * </pre>
 * @author 노명환
 * @since JDK17
 */
@Repository
@RequiredArgsConstructor
public class CommonRepositoryImpl implements CommonRepository{

    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    /** 과목 리스트 조회
     * @return SubjectCodeDto : 과목 정보 Dto
     * @throws PersistenceException
     */
    @Override
    public List<SubjectCodeDto> viewSubject() throws PersistenceException {
        return queryFactory
                .select(Projections.constructor(SubjectCodeDto.class,
                                subjectCode.code,
                                subjectCode.name,
                                subjectCode.maxScore))
                .from(subjectCode)
                .where(subjectCode.status.eq(1))
                .fetch();
    }

    /**
     * 유형 리스트 조회
     * @return CategoryCodeDto : 문제 유형 Dto
     * @throws PersistenceException
     */
    @Override
    public List<CategoryCodeDto> viewCategory(int subCode) throws PersistenceException {
        SubjectCode subjectCode = em.find(SubjectCode.class, subCode);
        return queryFactory
                .select(Projections.constructor(CategoryCodeDto.class,
                        problemCategory.code,
                        problemCategory.subjectCode.code,
                        problemCategory.name))
                .from(problemCategory)
                .where(problemCategory.subjectCode.code.eq(subCode)
                        .and(problemCategory.status.eq(1)))
                .fetch();
    }
}
