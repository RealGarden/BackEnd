package com.example.backend.controller.member;

import com.example.backend.domain.member.MemberRepository;
import com.example.backend.domain.member.MemberRequestDto;
import com.example.backend.domain.member.MemberResponseDto;
import com.example.backend.entity.member.MemberRole;
import com.example.backend.entity.member.Member;
import com.example.backend.service.member.LoginMember;
import com.example.backend.service.member.MemberCreateService;
import com.example.backend.service.member.MemberService;
import com.example.backend.service.member.MemberSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberApiController {

    private final MemberService memberService;
    private final MemberCreateService memberCreateService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/form")
    public Member createMember(@RequestBody MemberRequestDto requestDto){
        Member member =new Member(requestDto);
        MemberRole role = new MemberRole();
        role.setRoleName("BASIC");
        member.setRoles(Arrays.asList(role));
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setStatus("정상");
        System.out.println("정상등록");
        memberRepository.save(member);
        return member;
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
