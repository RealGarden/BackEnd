package com.example.backend.exception.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignUpException extends RuntimeException {
    public static final String SIGN_UP_FAIL_MESSAGE = "회원가입에 실패했습니다. : ";
    private static final Logger log = LoggerFactory.getLogger(SignUpException.class);

    public SignUpException(String message) {
        super(SIGN_UP_FAIL_MESSAGE + message);
        log.error(SIGN_UP_FAIL_MESSAGE + message);
    }

}