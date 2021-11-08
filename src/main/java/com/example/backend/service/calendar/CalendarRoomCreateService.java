package com.example.backend.service.calendar;

import com.example.backend.domain.calendar.CalendarRequestDto;
import com.example.backend.domain.calendar.CalendarResponseDto;
import com.example.backend.entity.calendar.CalendarRoom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CalendarRoomCreateService {

    private final CalendarRoomService calendarRoomService;

    public CalendarRoomCreateService(final CalendarRoomService calendarRoomService){this.calendarRoomService=calendarRoomService;}

    public CalendarResponseDto save(CalendarRequestDto calendarRequestDto) {
        CalendarRoom calendarRoom= calendarRoomService.save(calendarRequestDto);
        return CalendarResponseDto.from(calendarRoom);
    }
}
