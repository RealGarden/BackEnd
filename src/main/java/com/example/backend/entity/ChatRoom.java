package com.example.backend.entity;

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
    @JoinColumn(name="userdIdx")
    private User user;

    @Column(nullable = true,columnDefinition = "varchar(200)")
    private String chatSource;




}