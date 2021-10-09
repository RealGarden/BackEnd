package com.example.backend.controller.friend;

import com.example.backend.domain.friend.FriendDto;
import com.example.backend.domain.friend.FriendRepository;
import com.example.backend.entity.friend.FriendRelationship;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/friends")
public class FriendController {
    //friend request ,friend response
    private final FriendRepository friendRepository;


}
