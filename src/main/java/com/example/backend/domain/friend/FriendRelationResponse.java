package com.example.backend.domain.friend;

import com.example.backend.entity.friend.FriendRelationship;
import com.example.backend.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor
public class FriendRelationResponse {

    private Long Idx;
    private Long friendIdx;
    private String friendName;


    @Builder
    private FriendRelationResponse(Long Idx, Long friendIdx, String friendName) {
        this.Idx = Idx;
        this.friendIdx = friendIdx;
        this.friendName = friendName;
    }

    public static FriendRelationResponse from(FriendRelationship friend, Member relatedUser) {
        FriendRelationResponse friendResponse = FriendRelationResponse.builder()
                .friendIdx(friend.getFriendRelationshipIdx())
                .friendIdx(relatedUser.getMemberIdx())
                .friendName(relatedUser.getName())
                .build();


        return friendResponse;
    }
}