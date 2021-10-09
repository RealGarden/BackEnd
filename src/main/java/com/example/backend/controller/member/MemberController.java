package com.example.backend.controller;

import com.example.backend.domain.MemberRepository;
import com.example.backend.domain.MemberRequestDto;
import com.example.backend.entity.MemberRole;
import com.example.backend.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping({"/api/members"})
    public Member createMember(@RequestBody MemberRequestDto requestDto){
        Member member =new Member(requestDto);
        MemberRole role = new MemberRole();
        role.setRoleName("BASIC");
        member.setRoles(Arrays.asList(role));
        member.setPw(passwordEncoder.encode(member.getPw()));
        member.setStatus("정상");
        System.out.println("정상등록");
        memberRepository.save(member);
        return member;
    }

    @GetMapping({"/api/members"})
    public List<Member> list(){ return memberRepository.findAll();}



}
