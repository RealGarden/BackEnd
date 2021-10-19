package com.example.backend.entity.chat;

import com.example.backend.entity.member.Member;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.print.attribute.standard.DateTimeAtCompleted;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
public class ChatJoinRoom implements Serializable {

    @Id
    @JoinColumn(name="chatRoomIdx",referencedColumnName = "chatRoomIdx")
    @ManyToOne
    private ChatRoom chatRoom;

    @Id
    @JoinColumn(name="memberIdx",referencedColumnName = "memberIdx")
    @ManyToOne
    private Member member;

    @CreationTimestamp
    private Date registerDate;

    @Column(nullable = false,columnDefinition = "varchar(5)")
    private char status;




}
