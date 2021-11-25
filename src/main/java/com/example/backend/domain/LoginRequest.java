package com.example.backend.domain;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class LoginRequest {

    @NotNull
    private String id;

    @NotNull
    private String password;

    @Builder
    public LoginRequest(String id, String password) {
        this.id=id;
        this.password = password;
    }
}
