package com.example.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@ToString
@Data
@NoArgsConstructor
public class CalendarJoinRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long calendarJoinRoomIdx;

    @CreationTimestamp
    private Date registerDate;

    @Column(nullable = false,columnDefinition = "char(5)")
    private String status;

    @JoinColumn(name="userIdx")
    @ManyToOne
    private User user;

    @JoinColumn(name="calendarRoomIdx")
    @ManyToOne
    private CalendarRoom calendarRoom;
}
