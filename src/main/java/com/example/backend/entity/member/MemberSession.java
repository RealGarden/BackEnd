package com.example.backend.entity.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.servlet.http.HttpSessionBindingListener;

@Getter
@ToString
@NoArgsConstructor
public class MemberSession implements HttpSessionBindingListener {

    public static final String MEMBER_SESSION_KEY = "loginUser";

    private String id;
    private String name;

    @Builder
    private MemberSession(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MemberSession from(Member member) {
        return MemberSession.builder()
                .id(member.getId())
                .name(member.getName())
                .build();
    }
}