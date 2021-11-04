package com.example.backend.entity.calendar;

import com.example.backend.entity.member.Member;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class CalendarJoinRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long calendarJoinRoomIdx;

    @CreationTimestamp
    private Date registerDate;

    @Column(nullable = false,columnDefinition = "char(5)")
    private String status;

    @JoinColumn(name="memberIdx",referencedColumnName = "memberIdx")
    @ManyToOne
    private Member member;

    @JoinColumn(name="calendarRoomIdx",referencedColumnName = "calendarRoomIdx")
    @ManyToOne
    private CalendarRoom calendarRoom;
}
