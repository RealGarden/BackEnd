package com.example.backend.entity.calendar;

import com.example.backend.domain.calendar.CalendarRequestDto;
import com.example.backend.entity.member.Member;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CalendarRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long calendarRoomIdx;

    @CreationTimestamp
    private Date registerDate;

    @Column(nullable = false)
    private Date start;

    @Column(nullable = false)
    private Date end;

    @Column(nullable = false)
    private int color;

    @Column(nullable = false,columnDefinition = "varchar(10)")
    private String tag;

    @Column(nullable = false,columnDefinition = "varchar(20)")
    private String title;

    @Column(nullable = false,columnDefinition = "varchar(50)")
    private String contents;

    @JoinColumn(name="memberIdx",referencedColumnName = "memberIdx")
    @ManyToOne
    private Member member;

    @Builder
    public CalendarRoom(Date start,Date end,String tag,int color,String title,String contents){
        this.start=start;
        this.end=end;
        this.color=color;
        this.tag=tag;
        this.title=title;
        this.contents=contents;
    }
    public CalendarRoom(CalendarRequestDto requestDto){
        this.start=requestDto.getStart();
        this.end=requestDto.getEnd();
        this.color=requestDto.getColor();
        this.tag=requestDto.getTag();
        this.title=requestDto.getTitle();
        this.contents=requestDto.getContents();
    }


    public void update(CalendarRequestDto requestDto){
        this.start=requestDto.getStart();
        this.end=requestDto.getEnd();
        this.color=requestDto.getColor();
        this.tag=requestDto.getTag();
        this.title=requestDto.getTitle();
        this.contents=requestDto.getContents();
    }
}
