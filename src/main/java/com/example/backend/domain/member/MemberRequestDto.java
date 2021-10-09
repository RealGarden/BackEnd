package com.example.backend.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {
    private String profile;
    private String id;
    private String pw;
    private String name;
    private int age;
    private String phone;
    private String email;

//id,pw,name,age,phone,email

}
