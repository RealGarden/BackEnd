package com.example.backend.entity.friend;

import com.example.backend.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class FriendRequest {
    private static final String NULL_INPUT_MESSAGE="Null 값을 입력할 수 없습니다.";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long requestIdx;

    @JoinColumn(name="sender",nullable = false,updatable = false)
    @ManyToOne
    private Member senderId; //친구요청한사람 id


    @JoinColumn(name="receiverId",nullable = false,updatable = false)
    @ManyToOne
    private Member receiverId; //친구요청받은사람 id

    @Builder
    private FriendRequest(Member senderId,Member receiverId){
       validateNotNull(senderId);
       validateNotNull(receiverId);
       this.senderId.setId(senderId.getId());
       this.receiverId.setId(receiverId.getId());
    }
    private void validateNotNull(Member userId){
        if(userId.getId()==null)
            throw new IllegalArgumentException(NULL_INPUT_MESSAGE);
    }

    public List<FriendRelationship> createBidirectionalFriends() {
        return Arrays.asList(
                FriendRelationship.builder()
                        .userId(senderId)
                        .friendId(receiverId)
                       .build(),
                FriendRelationship.builder()
                        .userId(receiverId)
                        .friendId(senderId)
                        .build()
        );
    }

    public boolean matchUserId(Long userId) {
        return receiverId.equals(userId) || senderId.equals(userId);
    }

}
