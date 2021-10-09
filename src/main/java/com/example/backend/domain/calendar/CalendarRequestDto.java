package com.example.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

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
}