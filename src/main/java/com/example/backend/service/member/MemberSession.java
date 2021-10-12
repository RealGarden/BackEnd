package com.example.backend.service.member;

import com.example.backend.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.servlet.http.HttpSessionBindingListener;

@Getter
@ToString
@NoArgsConstructor
public class MemberSession implements HttpSessionBindingListener {

    public static final String USER_SESSION_KEY = "loginUser";

    private Long id;
    private String email;
    private String name;

    @Builder
    private MemberSession(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public static MemberSession from(Member member) {
        return MemberSession.builder()
                .id(member.getMemberIdx())
                .email(member.getEmail())
                .name(member.getName())
                .build();
    }
}