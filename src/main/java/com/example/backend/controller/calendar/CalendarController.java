package com.example.backend.controller.calendar;

import com.example.backend.domain.calendar.CalendarRepository;
import com.example.backend.domain.calendar.CalendarRequestDto;
import com.example.backend.entity.calendar.CalendarRoom;
import com.example.backend.service.calendar.CalendarRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CalendarController {
    private final CalendarRepository calendarRepository;
    private final CalendarRoomService calendarRoomService;

    @PostMapping({"/","/api/calendar"})
    public CalendarRoom createCalendarRoom(@RequestBody CalendarRequestDto requestDto){
        CalendarRoom calendarRoom=new CalendarRoom(requestDto);
        calendarRepository.save(calendarRoom);
        return calendarRoom;
    }

    @GetMapping("/api/calendar/{id}")
    public CalendarRoom getCalendarRoom(@PathVariable Long calendarRoomIdx){
        return calendarRepository.findById(calendarRoomIdx).get();
    }

    @PutMapping("/api/calendar/{id}")
    public Long updateCalendarRoom(@PathVariable Long calendarRoomIdx, @RequestBody CalendarRequestDto requestDto){
        calendarRoomService.update(calendarRoomIdx,requestDto);
        return calendarRoomIdx;
    }

    @DeleteMapping("/api/calendar/{id}")
    public Long deleteCalendarRoom(@PathVariable Long calendarRoomIdx){
        calendarRepository.deleteById(calendarRoomIdx);
        return calendarRoomIdx;
    }
}