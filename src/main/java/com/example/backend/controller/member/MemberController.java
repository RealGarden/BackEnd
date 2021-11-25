package com.example.backend.controller.member;

import com.example.backend.domain.member.MemberRepository;
import com.example.backend.domain.member.MemberRequestDto;
import com.example.backend.domain.member.MemberResponseDto;
import com.example.backend.entity.member.Member;
import com.example.backend.service.member.LoginMember;
import com.example.backend.service.member.MemberCreateService;
import com.example.backend.service.member.MemberService;
import com.example.backend.service.member.MemberSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private final MemberCreateService memberCreateService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberController(final MemberService memberService,
                            final MemberCreateService memberCreateService,
                            final MemberRepository memberRepository,
                            final PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.memberCreateService=memberCreateService;
        this.memberRepository=memberRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }


    @PostMapping("/signup")
    public String signup(
            @Valid @RequestBody MemberRequestDto memberRequestDto
            ) {
        memberService.registerUser(memberRequestDto);
        return "redirect:/";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MemberResponseDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(memberService.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberResponseDto> getUserInfo(@PathVariable String Id) {
        return ResponseEntity.ok(memberService.getUserWithAuthorities(Id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findUserResponseById(id));

    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> list(@LoginMember MemberSession memberSession) {
        return ResponseEntity.ok(memberService.findAllUsersWithoutCurrentUser(memberSession.getId()));
    }

}
