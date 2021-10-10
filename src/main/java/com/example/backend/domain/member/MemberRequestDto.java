package com.example.backend.domain.member;

import com.example.backend.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

@Getter
@NoArgsConstructor
public class MemberRequestDto {
    private String profile;
    private String id;
    private String password;
    private String name;
    private int age;
    private String phone;
    private String email;


    @Builder
    public MemberRequestDto(String profile,String id,int age, String phone,String email, String name, String password) {
        this.profile=profile;
        this.id=id;
        this.age=age;
        this.phone=phone;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .name(name)
                .age(age)
                .phone(phone)
                .profile(profile)
                .email(email)
                .password(password)
                .build();
    }
//id,pw,name,age,phone,email

}
