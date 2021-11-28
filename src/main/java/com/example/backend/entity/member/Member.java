package com.example.backend.entity.member;

import com.example.backend.domain.member.MemberRequestDto;
import com.example.backend.domain.member.MemberValidator;
import com.example.backend.entity.chat.ChatJoinRoom;
import com.example.backend.entity.chat.ChatRoom;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor //기본생성자
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Member {
    public static final String INVALID_PASSWORD_MESSAGE = "비밀번호가 틀렸습니다.";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberIdx;

    @Column(nullable = false,columnDefinition = "varchar(13)")
    private String id;

    @Column(nullable = false,length = 2000)
    private String password;

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
    @JoinColumn(name = "room_id",nullable=true)
    private Set<ChatJoinRoom> chatJoinRooms;

    @OneToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="user_idx",nullable=true)
    private Set<ChatRoom> chatRoom;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime loginAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime logoutAt;


    @Builder
    public Member(String id, String password, String name, int age, String phone, String email, String profile){
        MemberValidator.validateEmail(email);
        MemberValidator.validateName(name);
        MemberValidator.validatePassword(password);

        this.id=id;
        this.password = password;
        this.name=name;
        this.age=age;
        this.phone=phone;
        this.email = email;
        this.profile=profile;
        this.status="0";
        this.eventAlarm=true;
        this.friendAlarm=true;
        this.talkAlarm=true;
    }

    public Member(MemberRequestDto requestDto) {
        this.profile=requestDto.getProfile();
        this.id=requestDto.getId();
        this.password =requestDto.getPassword();
        this.name=requestDto.getName();
        this.age=requestDto.getAge();
        this.phone=requestDto.getPhone();
        this.email =requestDto.getEmail();
        this.status="0";
        this.eventAlarm=true;
        this.friendAlarm=true;
        this.talkAlarm=true;
    }

    public void update(MemberRequestDto requestDto) {
        this.profile=requestDto.getProfile();
        this.name=requestDto.getName();
        this.password =requestDto.getPassword();
        this.age=requestDto.getAge();
        this.phone=requestDto.getPhone();
        this.email =requestDto.getEmail();
    }

    public void matchPassword(String password) { //비밀번호 확인인
       if (!this.password.equals(password)) {
            throw new IllegalArgumentException(INVALID_PASSWORD_MESSAGE);
        }
    }

    public boolean matchId(Long id) {
        return (id != null) && (id.equals(getMemberIdx()));
    }

    public void uploadProfile(String profile) {
        this.profile = profile;
    }

    public void updateLoginAt(LocalDateTime loginAt) {
        this.loginAt = loginAt;
    }

    public void updateLogoutAt(LocalDateTime logoutAt) {
        this.logoutAt = logoutAt;
    }

    public boolean isLogin() {
        return loginAt.isAfter(logoutAt);
    }


    public boolean matchUserId(String id) {return (id != null) && (id.equals(getId()));}
}
