package com.example.backend.domain.calendar;

import com.example.backend.entity.calendar.CalendarRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Getter
@NoArgsConstructor
public class CalendarRequestDto {
    private Date start;
    private Date end;
    private int color;
    private String tag;
    private String title;
    private String contents;

    @Builder
    public CalendarRequestDto(Date start,Date end,int color,String tag,String title,String contents){
        this.start=start;
        this.end=end;
        this.color=color;
        this.tag=tag;
        this.title=title;
        this.contents=contents;
    }

    public CalendarRoom toEntity(){
        return CalendarRoom.builder()
                .start(start)
                .end(end)
                .color(color)
                .tag(tag)
                .title(title)
                .contents(contents)
                .build();
    }
}