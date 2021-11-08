package com.example.backend.service.calendar;

import com.example.backend.domain.calendar.CalendarRepository;
import com.example.backend.domain.calendar.CalendarRequestDto;
import com.example.backend.entity.calendar.CalendarRoom;
import com.example.backend.exception.calendar.CalendarDeleteException;
import com.example.backend.exception.member.SignUpException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CalendarRoomService {
    private final CalendarRepository calendarRepository;

    public static final String NOT_SAVE_CALENDAR_MESSAGE = "일정을 저장에 실패하였습니다..";
    public static final String DELETE_FAILED_CALENDAR_MESSAGE = "일정을 삭제에 실패하였습니다.";

    public CalendarRoom save(final CalendarRequestDto calendarRequestDto){
        try {
            return calendarRepository.save(calendarRequestDto.toEntity());
        } catch (Exception e) {
            throw new SignUpException(NOT_SAVE_CALENDAR_MESSAGE);
        }
    }
    @Transactional
    public Long update(Long calendarRoomIdx, CalendarRequestDto requestDto) {
        CalendarRoom calendarRoom = calendarRepository.findById(calendarRoomIdx).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        calendarRoom.update(requestDto);
        return calendarRoom.getCalendarRoomIdx();
    }

    public Long delete(final Long calendarRoomIdx){
        try {
            calendarRepository.deleteById(calendarRoomIdx);
            return calendarRoomIdx;
        } catch (Exception e) {
            throw new CalendarDeleteException(DELETE_FAILED_CALENDAR_MESSAGE);
        }
    }

    public List<CalendarRoom> showAll(String memberId){
        return calendarRepository.findAllByMember(memberId);
    }

}
