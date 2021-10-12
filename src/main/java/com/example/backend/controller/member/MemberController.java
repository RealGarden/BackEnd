package com.example.backend.controller.member;

import com.example.backend.domain.member.MemberRepository;
import com.example.backend.domain.member.MemberRequestDto;
import com.example.backend.domain.member.MemberResponseDto;
import com.example.backend.entity.member.MemberRole;
import com.example.backend.entity.member.Member;
import com.example.backend.service.member.MemberCreateService;
import com.example.backend.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberCreateService memberCreateService;
    //private final UserDeleteService userDeleteService;
    //private final UserImageUploadService userImageUploadService;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping({"/api/members"})
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
    public ResponseEntity<MemberResponseDto> show(@PathVariable String id) {
        return ResponseEntity.ok(memberService.findUserResponseById(id));

    }

    @PostMapping
    public ResponseEntity<MemberResponseDto> create(@RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto memberResponseDto = memberCreateService.create(memberRequestDto);
        URI uri = linkTo(UserApiController.class).slash(memberResponseDto.getId()).toUri();
        return ResponseEntity.created(uri).body(memberResponseDto);
    }



}
