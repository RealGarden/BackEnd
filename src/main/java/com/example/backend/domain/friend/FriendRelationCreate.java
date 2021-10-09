package com.example.backend.domain.friend;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendRelationCreate {

    private Long friendRelationId;

    @Builder
    public FriendRelationCreate(Long friendRelationId) {
        this.friendRelationId = friendRelationId;
    }
}