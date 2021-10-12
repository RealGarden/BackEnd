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
    private Long senderIdx;
    private String senderName;
    private Long receiverIdx;
    private String receiverName;

    @Builder
    private FriendRequestResponse(Long requestIdx, Long senderIdx, String senderName,
                              Long receiverIdx, String receiverName) {
        this.requestIdx = requestIdx;
        this.senderIdx = senderIdx;
        this.senderName = senderName;
        this.receiverIdx = receiverIdx;
        this.receiverName = receiverName;
    }

    public static FriendRequestResponse from(FriendRequest friendRequest, Member sender, Member receiver) {
        return FriendRequestResponse.builder()
                .requestIdx(friendRequest.getRequestIdx())
                .senderIdx(sender.getMemberIdx())
                .senderName(sender.getName())
                .receiverIdx(receiver.getMemberIdx())
                .receiverName(receiver.getName())
                .build();
    }
}