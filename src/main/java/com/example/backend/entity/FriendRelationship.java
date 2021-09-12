package com.example.backend.entity;

import com.example.backend.domain.FriendDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
@NoArgsConstructor
public class FriendRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long friendRelationshipIdx;

    @CreationTimestamp
    private Date registerDate;

    @Column(nullable = false,columnDefinition = "char(5)")
    private String status;

    @Column(nullable = true,columnDefinition = "varchar(20)")
    private String nickname;

    @JoinColumn(name="userMemberIdx")
    @ManyToOne
    private Member userMember;

    @JoinColumn(name="friendMemberIdx")
    @ManyToOne
    private Member friendMember;


    public FriendRelationship(FriendDto dto){
        this.nickname=dto.getNickname();
    }
}