package com.example.backend.domain.friend;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendRelationCreate {

    private String friendRelationId;

    @Builder
    public FriendRelationCreate(String friendRelationId) {
        this.friendRelationId = friendRelationId;
    }
}