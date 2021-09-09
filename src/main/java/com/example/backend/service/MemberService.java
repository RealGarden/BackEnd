package com.example.backend.service;

import com.example.backend.domain.MemberRepository;
import com.example.backend.domain.MemberRequestDto;
import com.example.backend.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private MemberRepository memberRepository;

    @Transactional
    public Long update(Long userIdx, MemberRequestDto requestDto) {
        Member member = memberRepository.findById(userIdx).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        member.update(requestDto);
        return member.getUserIdx();
    }
}