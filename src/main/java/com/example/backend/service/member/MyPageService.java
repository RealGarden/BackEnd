package com.example.backend.service.member;

import com.example.backend.domain.member.MyPageResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyPageService {

    private final MemberService memberService;

    public MyPageService(final MemberService memberService) {
        this.memberService = memberService;

    }

    @Transactional(readOnly = true)
    public MyPageResponse findUserResponseById(final String id) {
        return MyPageResponse.from(
                memberService.findByUserId(id),
                memberService.findUserResponseOfFriendsById(id));
    }
}