package com.example.backend.service;

import com.example.backend.domain.UserRepository;
import com.example.backend.domain.UserRequestDto;
import com.example.backend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    @Transactional
    public Long update(Long userIdx, UserRequestDto requestDto) {
        User user = userRepository.findById(userIdx).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        user.update(requestDto);
        return user.getUserIdx();
    }
}