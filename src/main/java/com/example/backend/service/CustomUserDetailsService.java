package com.example.backend.service;

import com.example.backend.domain.member.MemberRepository;
import com.example.backend.entity.member.Member;
import com.example.backend.service.member.SecurityMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        return Optional.ofNullable(memberRepository.findById(id))
                .filter(m -> m != null)
                .map(m -> new SecurityMember(m)).get();
    }

}