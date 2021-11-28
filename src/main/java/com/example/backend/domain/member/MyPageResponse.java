package com.example.backend.domain.member;

import com.example.backend.entity.member.Member;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MyPageResponse {

    private MemberResponseDto memberResponseDto;
    private List<MemberResponseDto> friends;

    @Builder
    private MyPageResponse(final MemberResponseDto member, final List<MemberResponseDto> friends) {
        this.memberResponseDto = member;
        this.friends = friends;
    }

    public static MyPageResponse from(Member member, List<MemberResponseDto> friends) {
        MyPageResponse myPageResponse = MyPageResponse.builder()
                .member(MemberResponseDto.from(member))
                .friends(friends)
                .build();




        return myPageResponse;
    }


}