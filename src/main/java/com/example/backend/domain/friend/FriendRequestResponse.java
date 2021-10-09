package com.example.backend.domain.friend;

import com.example.backend.entity.friend.FriendRequest;
import com.example.backend.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendRequestResponse {

    private Long requestIdx;
    private String senderId;
    private String senderName;
    private String receiverId;
    private String receiverName;

    @Builder
    private FriendRequestResponse(Long requestIdx, String senderId, String senderName,
                              String receiverId, String receiverName) {
        this.requestIdx = requestIdx;
        this.senderId = senderId;
        this.senderName = senderName;
        this.receiverId = receiverId;
        this.receiverName = receiverName;
    }

    public static FriendRequestResponse from(FriendRequest friendRequest, Member sender, Member receiver) {
        return FriendRequestResponse.builder()
                .requestIdx(friendRequest.getRequestIdx())
                .senderId(sender.getId())
                .senderName(sender.getName())
                .receiverId(receiver.getId())
                .receiverName(receiver.getName())
                .build();
    }
}