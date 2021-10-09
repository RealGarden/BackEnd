package com.example.backend.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatRoomIdx;

    @CreationTimestamp
    private Date registerDate;

    @Column(nullable = false,columnDefinition = "varchar(20)")
    private String roomName;

    @Column(nullable = false) //개인,단체
    private int type1;

    @Column(nullable = false) //일반,스터디,화상
    private int type2;

    @Column(nullable = true)
    private int maxNum;

    @ManyToOne
    @JoinColumn(name="memberIdx")
    private Member member;

    @Column(nullable = true,columnDefinition = "varchar(200)")
    private String chatSource;

    @OneToMany
    @JoinColumn(name="user_idx")
    private Set<ChatJoinRoom> chatJoinRood;



}