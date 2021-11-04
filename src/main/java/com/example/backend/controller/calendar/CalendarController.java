package com.example.backend.controller.calendar;

import com.example.backend.domain.calendar.CalendarRepository;
import com.example.backend.domain.calendar.CalendarRequestDto;
import com.example.backend.domain.calendar.CalendarResponseDto;
import com.example.backend.entity.calendar.CalendarRoom;
import com.example.backend.service.calendar.CalendarRoomCreateService;
import com.example.backend.service.calendar.CalendarRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/calendars")
public class CalendarController {
    private final CalendarRepository calendarRepository;
    private final CalendarRoomService calendarRoomService;
    private final CalendarRoomCreateService calendarRoomCreateService;

    @PostMapping("/api/calendar")
    public ResponseEntity<CalendarResponseDto> createCalendar(@RequestBody CalendarRequestDto calendarRequestDto){
        CalendarResponseDto calendarResponseDto=calendarRoomCreateService.save(calendarRequestDto);
        return ResponseEntity.ok().body(calendarResponseDto);
    }

    @GetMapping("/{memberId}")
    public List<CalendarRoom> showAllCalendarRoom(@PathVariable String memberId){
            return calendarRoomService.showAll(memberId);
    }

    @GetMapping("/{memberId}/{calendarRoomIdx}")
    public ResponseEntity<CalendarRoom> showCalendarRoom(@PathVariable String memberId,@PathVariable Long calendarRoomIdx){
        CalendarRoom calendarRoom=calendarRoomService.show(memberId,calendarRoomIdx);
        return ResponseEntity.ok().body(calendarRoom);
    }
    @PutMapping("/api/calendar/{id}")
    public Long updateCalendarRoom(@PathVariable Long id, @RequestBody CalendarRequestDto requestDto){
        calendarRoomService.update(id,requestDto);
        return id;
    }

    @DeleteMapping("/api/calendar/{id}")
    public Long deleteCalendarRoom(@PathVariable Long id){
        return calendarRoomService.delete(id);
    }

}