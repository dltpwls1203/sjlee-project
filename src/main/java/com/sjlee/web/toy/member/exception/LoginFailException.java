package com.sjlee.web.toy.member.exception;

public class LoginFailException extends RuntimeException{

    public LoginFailException() {
        super("존재하지 않는 아이디 입니다.");
    }

    public LoginFailException(String message) {
        super(message);
    }
}
