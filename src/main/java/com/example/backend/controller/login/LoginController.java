package com.example.backend.controller.login;

import com.example.backend.domain.LoginRequest;
import com.example.backend.domain.member.MemberResponseDto;
import com.example.backend.service.LoginService;
import com.example.backend.service.member.LoginMember;
import com.example.backend.service.member.MemberService;
import com.example.backend.entity.member.MemberSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.example.backend.entity.member.MemberSession.MEMBER_SESSION_KEY;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;
    private final MemberService memberService;
    private static final Logger logger= LoggerFactory.getLogger(LoginController.class);

    public LoginController(final LoginService loginService, final MemberService memberService) {
        this.loginService = loginService;
        this.memberService = memberService;
    }
    @GetMapping("/login")
    public String login() {
        return "redirect:/api/login";
    }

    @PostMapping("/login")
    public ResponseEntity<MemberResponseDto> login(@RequestBody LoginRequest loginRequest, HttpSession httpSession) {
        System.out.println("============");
        logger.info(loginRequest.getId());
        logger.info(loginRequest.getPassword());
        System.out.println("============");
        MemberSession memberSession = loginService.login(loginRequest);
        httpSession.setAttribute(MEMBER_SESSION_KEY, memberSession);
        return ResponseEntity.ok(memberService.findUserResponseById(memberSession.getId()));
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@LoginMember MemberSession memberSession, HttpSession httpSession) {
        loginService.logout(memberSession.getId());
        httpSession.removeAttribute(MEMBER_SESSION_KEY);
        return ResponseEntity.ok().build();
    }
}

