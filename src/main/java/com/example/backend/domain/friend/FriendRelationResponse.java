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

    private Long friendIdx;
    private String friendId;
    private String friendName;


    @Builder
    private FriendRelationResponse(Long friendIdx, String friendId, String friendName) {
        this.friendIdx = friendIdx;
        this.friendId = friendId;
        this.friendName = friendName;
    }

    public static FriendRelationResponse from(FriendRelationship friend, Member relatedUser) {
        FriendRelationResponse friendResponse = FriendRelationResponse.builder()
                .friendIdx(friend.getFriendRelationshipIdx())
                .friendId(relatedUser.getId())
                .friendName(relatedUser.getName())
                .build();


        return friendResponse;
    }
}