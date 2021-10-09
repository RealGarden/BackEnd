package com.example.backend.entity.member;

import com.example.backend.domain.member.MemberRequestDto;
import com.example.backend.entity.chat.ChatJoinRoom;
import com.example.backend.entity.chat.ChatRoom;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor //기본생성자
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberIdx;
    @Column(nullable = false,columnDefinition = "varchar(13)")
    private String id;

    @Column(nullable = false,length = 2000)
    private String pw;

    @Column(nullable = false,columnDefinition = "varchar(20)")
    private String name;

    @Column(nullable = true,columnDefinition = "varchar(100)")
    private String statusMessage;

    @Column(nullable = false,columnDefinition = "varchar(20)")
    private String email;

    @Column(nullable = false,columnDefinition = "char(5)")
    private String status;

    @Column(nullable = false,columnDefinition = "varchar(11)")
    private String phone;

    @Column(nullable = false,columnDefinition = "int")
    private int age;

    @Column(nullable = false,columnDefinition = "varchar(200)")
    private String profile;

    @Column(nullable = false,columnDefinition = "boolean default true")
    private boolean friendAlarm;

    @Column(nullable = false,columnDefinition = "boolean default true")
    private boolean talkAlarm;

    @Column(nullable = false,columnDefinition = "boolean default true")
    private boolean eventAlarm;

    @CreationTimestamp
    private Date registerDate;

    @UpdateTimestamp
    private Date editDate;

    @Column(nullable = true)
    private String withrawDate;

    @Column(nullable = true,columnDefinition = "varchar(100)")
    private String withrawReason;

    @OneToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="id")
    private List<MemberRole> roles;

    @OneToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Set<ChatJoinRoom> chatJoinRooms;

    @OneToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="user_idx")
    private Set<ChatRoom> chatRoom;


    public Member(String id, String pw, String name, int age, String phone, String Uemail, String profile){
        this.id=id;
        this.pw=pw;
        this.name=name;
        this.age=age;
        this.phone=phone;
        this.email = Uemail;
        this.profile=profile;
        this.eventAlarm=true;
        this.friendAlarm=true;
        this.talkAlarm=true;
    }

    public Member(MemberRequestDto requestDto) {
        this.profile=requestDto.getProfile();
        this.id=requestDto.getId();
        this.pw=requestDto.getPw();
        this.name=requestDto.getName();
        this.age=requestDto.getAge();
        this.phone=requestDto.getPhone();
        this.email =requestDto.getEmail();
        this.eventAlarm=true;
        this.friendAlarm=true;
        this.talkAlarm=true;
    }

    public void update(MemberRequestDto requestDto) {
        this.profile=requestDto.getProfile();
        this.name=requestDto.getName();
        this.pw=requestDto.getPw();
        this.age=requestDto.getAge();
        this.phone=requestDto.getPhone();
        this.email =requestDto.getEmail();
    }

}
