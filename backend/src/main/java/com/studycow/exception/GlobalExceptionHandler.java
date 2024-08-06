package com.studycow.exception;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 전역 예외 처리
     * @param e
     * @return ResponseEntity
     */
    @ExceptionHandler(java.lang.Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException(java.lang.Exception e) {
        logger.error("Unexpected error occurred", e);
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다.", e.getMessage());
    }

    /**
     * 메서드 값 검증 예외처리
     * @param e
     * @return ResponseEntity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("Validation error occurred", e);

        Map<String, String> validationErrors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                validationErrors.put(error.getField(), error.getDefaultMessage())
        );

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "입력값 검증 실패",
                validationErrors,
                e.getBindingResult().getObjectName(),
                LocalDateTime.now()
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }

    // 여기에 필요한 다른 예외 처리 메서드들을 추가 가능
    // ex: @ExceptionHandler(ResourceNotFoundException.class) 등

    /**
     * 커스텀 예외처리 생성
     * @param status
     * @param message
     * @param errorDetails
     * @return ResponseEntity<ErrorResponse>
     */
    private ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus status, String message, String errorDetails) {
        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                message,
                errorDetails,
                null, // 기본값 null
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    /**
     * ErrorResponse 클래스 정의
     */
    @Getter
    private static class ErrorResponse {
        private final int status;
        private final String message;
        private final Object errors;
        private final String objectName;
        private final LocalDateTime timestamp;

        public ErrorResponse(int status, String message, Object errors, String objectName, LocalDateTime timestamp) {
            this.status = status;
            this.message = message;
            this.errors = errors;
            this.objectName = objectName;
            this.timestamp = timestamp;
        }


    }
}