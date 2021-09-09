package com.example.backend.controller;

import com.example.backend.domain.UserRepository;
import com.example.backend.domain.UserRequestDto;
import com.example.backend.domain.UserRole;
import com.example.backend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping({"","/api/users"})
    public User createMember(@RequestBody UserRequestDto requestDto){
        User user =new User(requestDto);
        UserRole role = new UserRole();
        role.setRoleName("BASIC");
        user.setRoles(Arrays.asList(role));
        user.setPw(passwordEncoder.encode(user.getPw()));
        user.setStatus("정상");
        userRepository.save(user);
        return user;
    }

    @GetMapping({"/api/user"})
    public List<User> list(){ return userRepository.findAll();}

    @GetMapping("/api/login")
    public void login(){
        System.out.println("logggggg");
    }


}
