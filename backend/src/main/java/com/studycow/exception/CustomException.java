package com.studycow.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends java.lang.Exception {
    private final ErrorCode errorCode;

}
