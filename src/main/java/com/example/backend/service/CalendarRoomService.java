package com.example.backend.service;

import com.example.backend.domain.calendar.CalendarRepository;
import com.example.backend.domain.calendar.CalendarRequestDto;
import com.example.backend.entity.calendar.CalendarRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CalendarRoomService {
    private CalendarRepository calendarRepository;

    @Transactional
    public Long update(Long calendarRoomIdx, CalendarRequestDto requestDto) {
        CalendarRoom calendarRoom = calendarRepository.findById(calendarRoomIdx).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        calendarRoom.update(requestDto);
        return calendarRoom.getCalendarRoomIdx();
    }
}
