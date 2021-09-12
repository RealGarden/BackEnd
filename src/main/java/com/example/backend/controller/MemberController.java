package com.example.backend.controller;

import com.example.backend.domain.MemberRepository;
import com.example.backend.domain.MemberRequestDto;
import com.example.backend.domain.MemberRole;
import com.example.backend.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping({"","/api/users"})
    public Member createMember(@RequestBody MemberRequestDto requestDto){
        Member member =new Member(requestDto);
        MemberRole role = new MemberRole();
        role.setRoleName("BASIC");
        member.setRoles(Arrays.asList(role));
        member.setPw(passwordEncoder.encode(member.getPw()));
        member.setStatus("정상");
        memberRepository.save(member);
        return member;
    }

    @GetMapping({"/api/user"})
    public List<Member> list(){ return (List<Member>) memberRepository.findAll();}

    @GetMapping("/api/login")
    public void login(){
        System.out.println("logggggg");
    }


}
