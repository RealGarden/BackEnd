package com.example.backend.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequest {
    private String id;
    private String password;

    @Builder
    public LoginRequest(String id, String password) {
        this.id=id;
        this.password = password;
    }
}
