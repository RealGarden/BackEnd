package com.example.backend.exception.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class MemberDeleteException extends RuntimeException{
    public static final String MEMBER_DELETE_FAIL_MESSAGE = "회원 탈퇴에 실패했습니다.";
    private static final Logger log = LoggerFactory.getLogger(MemberDeleteException.class);

    public MemberDeleteException() {
        super(MEMBER_DELETE_FAIL_MESSAGE);
        log.error(MEMBER_DELETE_FAIL_MESSAGE);
    }
}
