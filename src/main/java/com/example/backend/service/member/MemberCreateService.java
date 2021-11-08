package com.example.backend.service.member;

import com.example.backend.domain.member.MemberRequestDto;
import com.example.backend.domain.member.MemberResponseDto;
import com.example.backend.entity.member.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberCreateService {

    private final MemberService memberService;

    public MemberCreateService(final MemberService memberService) {
        this.memberService = memberService;
    }

    public MemberResponseDto create(MemberRequestDto memberRequestDto) {
        Member member= memberService.save(memberRequestDto);
        return MemberResponseDto.from(member);
    }
}