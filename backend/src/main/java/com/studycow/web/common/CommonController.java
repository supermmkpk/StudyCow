package com.studycow.web.common;


import com.studycow.dto.common.CategoryCodeDto;
import com.studycow.dto.common.SubjectCodeDto;
import com.studycow.service.common.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <pre>
 *     과목, 유형 등 공통 컨트롤러 클래스
 * </pre>
 * @author 노명환
 * @since JDK17
 */

@Tag(name = "Common", description = "과목, 유형 등의 공통사용")
@RestController
@RequestMapping("/common")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CommonController {

    private final CommonService commonService;

    @Operation(summary = "과목 리스트 조회", description = "과목의 대분류를 조회합니다.")
    @GetMapping("/subject")
    public ResponseEntity<?> viewSubject() {
        try {
            List<SubjectCodeDto> subjectCodeDtoList = commonService.viewSubject();
            return ResponseEntity.ok(subjectCodeDtoList);
        } catch(Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "문제 유형 리스트 조회", description = "과목의 문제 유형을 조회합니다.")
    @GetMapping("/subject/{subCode}")
    public ResponseEntity<?> viewCategory(@PathVariable int subCode) {
        try {
            List<CategoryCodeDto> categoryCodeDtoList = commonService.viewCategory(subCode);
            return ResponseEntity.ok(categoryCodeDtoList);
        } catch(Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
