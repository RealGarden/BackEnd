package com.example.backend.controller.friend;

import com.example.backend.exception.NotLoginException;
import com.example.backend.exception.friend.AlreadyFriendException;
import com.example.backend.exception.friend.FriendAskFailException;
import com.example.backend.exception.friend.MismatchedMemberException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice(basePackageClasses = FriendApiController.class)
public class FriendApiControllerAdvice {

    @ExceptionHandler({EntityNotFoundException.class, NotLoginException.class,
            FriendAskFailException.class, MismatchedMemberException.class,
            IllegalArgumentException.class, AlreadyFriendException.class})
    protected ResponseEntity<ErrorMessage> handleException(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception);
        return ResponseEntity.badRequest().body(errorMessage);
    }

}