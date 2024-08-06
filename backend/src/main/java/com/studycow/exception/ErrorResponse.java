package com.studycow.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private ErrorCode errorCode;
    private List<FieldError> validationErrors;


    @Getter
    @Setter
    @NoArgsConstructor
    public static class FieldError{
        private String field;
        private String message;
    }

    public ErrorResponse(ErrorCode errorCode, List<FieldError> validationErrors) {
        this.errorCode = errorCode;
        this.validationErrors = validationErrors;
    }
    public ErrorResponse(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
