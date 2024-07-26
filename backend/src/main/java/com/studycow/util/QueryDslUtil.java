package com.studycow.util;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.studycow.dto.listoption.ListOptionDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * QueryDSL 유틸 클래스
 *
 * @author 박봉균
 * @since JDK17
 */
@Component
public class QueryDslUtil {


    /**
     * 테이블 칼럼 Path Object
     *
     * @param parent 대상 테이블
     * @param fieldName 칼럼명
     * @return Path<Object>
     */
    public static Path<Object> getColumnPath(Path<?> parent, String fieldName) {
        return Expressions.path(Object.class, parent, fieldName);
    }

    /**
     * 정렬 조건 만들기
     * @param order 정렬 방향
     * @param parent 대상 테이블
     * @param fieldName 칼럼명
     * @return OrderSpecifier<?>
     */
    public static OrderSpecifier<?> getSortedColumn(Order order, Path<?> parent, String fieldName) {
        return new OrderSpecifier(order, getColumnPath(parent, fieldName));
    }

    /**
     * 정렬을 동적으로 구현
     *
     * @param option 검색 및 정렬 조건
     * @param parent 정렬 대상 엔터티
     * @param defaultParent 기본 정렬 대상 엔터티
     * @param defaultFieldName 기본 정렬 대상 칼럼
     * @return OrderSpecifier[] 정렬 조건 배열
     */
    public static OrderSpecifier[] createOrderSpecifier(ListOptionDto option,
                                                  Path<?> parent,
                                                  Path<?> defaultParent,
                                                  String defaultFieldName) {
        //정렬 기준 및 정렬 방향
        String sortKey = option.getSortKey();
        Order direction;
        if(option.getIsDESC() == null) {
            direction = Order.ASC;
        } else if(option.getIsDESC()) {
            direction = Order.DESC;
        } else {
            direction = Order.ASC;
        }

        List<OrderSpecifier> orderSpecifierList = new ArrayList<>();

        // 정렬 기준이 null이 아니라면
        if (sortKey != null && !sortKey.isBlank()) {
            orderSpecifierList.add(getSortedColumn(direction, parent, sortKey));
        } else {
            orderSpecifierList.add(getSortedColumn(direction, defaultParent, defaultFieldName));
        }

        return orderSpecifierList.toArray(new OrderSpecifier[orderSpecifierList.size()]);
    }
}
