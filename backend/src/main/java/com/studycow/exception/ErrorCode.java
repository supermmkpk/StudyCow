package com.studycow.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SUCCESS(HttpStatus.OK, "요청 및 응답 성공"),
    DUPLICATE_USERNAME(HttpStatus.CONFLICT,"중복된 유저 이름입니다."),
    DUPLICATE_USEREMAIL(HttpStatus.CONFLICT,"중복된 유저 이메일입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"서버 오류 발생"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED,"유효하지 않은 토큰입니다. 재로그인 해주세요"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"해당하는 유저를 찾을 수 없습니다"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST,"잘못된 요청입니다."),
    DUPLICATE_RESOURCE(HttpStatus.CONFLICT,"중복된 요청입니다"),
    NO_AUTHENTICAION(HttpStatus.UNAUTHORIZED,"접근 권한이 없습니다. 로그인해주세요");

    private HttpStatus status;
    private final String message;
}




