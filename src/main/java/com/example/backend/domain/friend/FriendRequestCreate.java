package com.example.backend.domain.friend;

import com.example.backend.entity.friend.FriendRequest;
import com.example.backend.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendRequestCreate {

    private Member receiver;

    @Builder
    public FriendRequestCreate(Long receiverId) {
        receiver.setMemberIdx(receiverId);
    }

    public FriendRequest toEntity(Member sender) {
        return FriendRequest.builder()
                .senderId(sender)
                .receiverId(receiver)
                .build();
    }
}