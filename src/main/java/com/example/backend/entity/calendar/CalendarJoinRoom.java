package com.example.backend.entity;

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

    @JoinColumn(name="memberIdx")
    @ManyToOne
    private Member member;

    @JoinColumn(name="calendarRoomIdx")
    @ManyToOne
    private CalendarRoom calendarRoom;
}
