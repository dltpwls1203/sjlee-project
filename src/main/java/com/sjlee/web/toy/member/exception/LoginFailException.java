package com.sjlee.web.toy.member.exception;

public class LoginFailException extends RuntimeException{

    public LoginFailException() {
        super("아이디 또는 비밀번호가 올바르지 않습니다.");
    }

    public LoginFailException(String message) {
        super(message);
    }
}
