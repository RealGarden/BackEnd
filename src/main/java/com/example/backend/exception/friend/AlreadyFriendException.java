package com.example.backend.exception.friend;

public class AlreadyFriendException extends RuntimeException {

    public AlreadyFriendException(String message) {
        super(message);
    }
}