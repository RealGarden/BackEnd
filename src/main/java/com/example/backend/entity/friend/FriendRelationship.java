package com.example.backend.entity;

import com.example.backend.domain.FriendDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class FriendRelationship  {
    private static final String NULL_INPUT_MESSAGE="Null 값을 입력할 수 없습니다.";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long friendRelationshipIdx;

    @CreationTimestamp
    private Date registerDate;

    @Column(nullable = false,columnDefinition = "char(5)")
    private String status;

    @Column(nullable = true,columnDefinition = "varchar(20)")
    private String nickname;

    @JoinColumn(name="userId",nullable = false,updatable = false)
    @ManyToOne
    private Member userId;

    @JoinColumn(name="friendId",nullable = false,updatable = false)
    @ManyToOne
    private Member friendId;

    @Builder
    private FriendRelationship(Member userId,Member friendId){
        validateNotNull(userId);
        validateNotNull(friendId);
        this.friendId=friendId;
        this.userId=userId;
    }

    private void validateNotNull(Member userId){
        if(userId==null)
            throw new IllegalArgumentException(NULL_INPUT_MESSAGE);
    }

    public boolean matchUserId(Member userId){
        return this.userId.equals(userId);
    }

    public  boolean matchFriendId(Member userId){
        return  this.friendId.equals(userId);
    }

//    public FriendRelationship(FriendDto dto){
//        this.nickname=dto.getNickname();
//    }
}