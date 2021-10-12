package com.example.backend.service.member;

import com.example.backend.domain.member.MemberRequestDto;
import com.example.backend.domain.member.MemberResponseDto;
import com.example.backend.entity.member.Member;
import com.example.backend.service.member.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberCreateService {

    private final MemberService memberService;
    private final IntroductionService introductionService;

    public MemberCreateService(final MemberService memberService, final IntroductionService introductionService) {
        this.memberService = memberService;
        this.introductionService = introductionService;
    }

    public MemberResponseDto create(MemberRequestDto memberRequestDto) {
        Member member= memberService.save(memberRequestDto);
        introductionService.save(member.getId());
        return MemberResponseDto.from(member);
    }
}