package com.example.backend.exception.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemberMismatchException extends RuntimeException {

    public static final String USER_MISMATCH_MESSAGE = "일치하지 않는 유저입니다.";
    private static final Logger log = LoggerFactory.getLogger(MemberMismatchException.class);

    public MemberMismatchException() {
        super(USER_MISMATCH_MESSAGE);
        log.error(USER_MISMATCH_MESSAGE);
    }

    public MemberMismatchException(String message) {
        super(message);
    }
}
