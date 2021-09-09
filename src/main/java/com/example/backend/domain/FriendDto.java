package com.example.backend.domain;

import lombok.Getter;

@Getter
public class FriendDto { //수락,거절에 대한 정보?
    private Long userIdx;
    private Long friendIdx;
    private String nickname;
    //private  String
}
