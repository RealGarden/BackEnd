package com.example.backend.domain.calendar;

import com.example.backend.entity.calendar.CalendarRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class CalendarResponseDto {
    private Date start;
    private Date end;
    private int color;
    private String tag;
    private String title;
    private String contents;

    @Builder
    public CalendarResponseDto(Date start,Date end,int color,String tag,String title,String contents){
        this.start=start;
        this.end=end;
        this.color=color;
        this.tag=tag;
        this.title=title;
        this.contents=contents;
    }

    public static CalendarResponseDto from(CalendarRoom calendarRoom){
        CalendarResponseDto calendarResponseDto=CalendarResponseDto.builder()
                .start(calendarRoom.getStart())
                .end(calendarRoom.getEnd())
                .color(calendarRoom.getColor())
                .tag(calendarRoom.getTag())
                .title(calendarRoom.getTitle())
                .contents(calendarRoom.getContents())
                .build();

        return calendarResponseDto;
    }
}
