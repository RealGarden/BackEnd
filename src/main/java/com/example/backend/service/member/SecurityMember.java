package com.example.backend.service.member;

import com.example.backend.entity.member.MemberRole;
import com.example.backend.entity.member.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SecurityMember extends User {
    private static final String ROLE_PREFIX = "ROLE_";
    private static final long serialVersionUID = 1L;

    public SecurityMember(Member member) {
        super(member.getId(), member.getPassword(), makeGrantedAuthority(member.getRoles()));
    }


    private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles){
        List<GrantedAuthority> list = new ArrayList<>();
        roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
        return list;
    }
}
