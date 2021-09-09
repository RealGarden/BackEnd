package com.example.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Chat{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatIdx;

    @CreationTimestamp
    private Date registerDate;

    @Column(nullable = false)
    private boolean isFacetalk;

    @CreationTimestamp
    private Date connectedDate;

    private Date stopDate;

    @JoinColumn(name="userdIdx")
    @ManyToOne
    private User user;

    @JoinColumn(name="chatRoomIdx")
    @ManyToOne
    private ChatRoom chatRoom;

    @Column(nullable = false)
    private int unreadCnt;

    @Column(nullable = true,columnDefinition = "varchar(200)")
    private String backgroundImg;




}