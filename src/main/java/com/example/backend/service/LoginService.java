package com.example.backend.service;

import com.example.backend.domain.LoginRequest;
import com.example.backend.entity.member.Member;
import com.example.backend.exception.LoginFailException;
import com.example.backend.service.member.MemberService;
import com.example.backend.entity.member.MemberSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class LoginService {

    private final MemberService memberService;

    public LoginService(final MemberService userService) {
        this.memberService = userService;
    }

    public MemberSession login(final LoginRequest loginRequest) {
        try {
            Member member = memberService.findByUserId(loginRequest.getId());
            member.matchPassword(loginRequest.getPassword());
            checkLoginAndUpdateLogoutAt(member);
            member.updateLoginAt(LocalDateTime.now());
            return MemberSession.from(member);
        } catch (Exception e) {
            throw new LoginFailException(e.getMessage());
        }
    }

    public void logout(final String userId) {
        Member member = memberService.findByUserId(userId);
        checkLoginAndUpdateLogoutAt(member);
    }

    private void checkLoginAndUpdateLogoutAt(Member member) {
        if (member.isLogin()) {
            member.updateLogoutAt(LocalDateTime.now());
        }
    }
}
