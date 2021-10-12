package com.example.backend.exception.friend;

public class MismatchedMemberException extends RuntimeException {

    public MismatchedMemberException(String message) {
        super(message);
    }
}