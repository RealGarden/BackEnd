package com.example.backend.domain.member;

import com.example.backend.entity.member.Member;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Optional;

@Getter
@ToString
@NoArgsConstructor
public class MemberResponseDto {

    private Long idx;
    private String id;
    private String name;
    private String profile;
    private boolean isLogin;

    @Builder
    public MemberResponseDto(final Long idx, final String id, final String name, final String profile, final boolean isLogin) {
        this.idx = idx;
        this.id = id;
        this.name = name;
        this.profile = profile;
        this.isLogin = isLogin;
    }

    public static MemberResponseDto from(Member member) {
        MemberResponseDto memberResponsedto = MemberResponseDto.builder()
                .idx(member.getMemberIdx())
                .id(member.getId())
                .name(member.getName())
                .build();

        Optional<String> maybeProfile = getFileFeatureOfProfile(member.getProfile());
        maybeProfile.ifPresent(profile -> memberResponsedto.profile = profile.getBytes());

        return memberResponsedto;
    }

    private static Optional<String> getFileFeatureOfProfile(final String profile) {
        return Optional.ofNullable(profile);
    }
}