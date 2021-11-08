package com.example.backend.controller.login;

import com.example.backend.domain.LoginRequest;
import com.example.backend.domain.member.MemberResponseDto;
import com.example.backend.service.LoginService;
import com.example.backend.service.member.LoginMember;
import com.example.backend.service.member.MemberService;
import com.example.backend.service.member.MemberSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static com.example.backend.service.member.MemberSession.MEMBER_SESSION_KEY;

@RestController
@RequestMapping("/api/members")
public class LoginController {

    private final LoginService loginService;
    private final MemberService memberService;

    public LoginController(final LoginService loginService, final MemberService memberService) {
        this.loginService = loginService;
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<MemberResponseDto> login(@RequestBody LoginRequest loginRequest, HttpSession httpSession) {
        MemberSession memberSession = loginService.login(loginRequest);
        httpSession.setAttribute(MEMBER_SESSION_KEY, memberSession);
        return ResponseEntity.ok(memberService.findUserResponseById(memberSession.getId()));
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@LoginMember MemberSession memberSession, HttpSession httpSession) {
        loginService.logout(memberSession.getId());
        httpSession.removeAttribute(MEMBER_SESSION_KEY);
        return ResponseEntity.ok().build();
    }
}

