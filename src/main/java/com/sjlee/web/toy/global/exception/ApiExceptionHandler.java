package com.sjlee.web.toy.global.exception;

import com.sjlee.web.toy.global.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DuplicateUserIdException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateEmail() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.fail(
                        "DUPLICATE_EMAIL",
                        "이미 사용 중인 아이디입니다"
                ));
    }
}
