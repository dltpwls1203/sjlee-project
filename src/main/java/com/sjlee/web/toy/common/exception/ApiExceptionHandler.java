package com.sjlee.web.toy.common.exception;

import com.sjlee.web.toy.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DuplicateUserIdException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateUserId() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.fail(
                        "DUPLICATE_USER_ID",
                        "이미 사용 중인 아이디입니다"
                ));
    }
}
