package com.example.backend.controller;

import com.example.backend.domain.FriendDto;
import com.example.backend.domain.FriendRepository;
import com.example.backend.entity.FriendRelationship;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FriendController {
    //friend request ,friend response
    private final FriendRepository friendRepository;

    @PostMapping("/")
    public void friendRequest(@RequestBody FriendDto dto){
        FriendRelationship friendRelationship=new FriendRelationship(dto);
        friendRelationship.setStatus("대기");
        if(friendRelationship.getNickname().isEmpty()){
            //friendRelationship.setNickname(friendRepository.findById();
        }
        else{
            friendRelationship.setNickname(dto.getNickname());
        }
    }
}
