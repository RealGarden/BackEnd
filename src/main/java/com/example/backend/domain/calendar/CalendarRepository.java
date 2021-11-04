package com.example.backend.domain.calendar;

import com.example.backend.entity.calendar.CalendarRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<CalendarRoom, Long> {
    List<CalendarRoom> findAllByMember(String id);

    CalendarRoom findByMemberAndIdx(String id, Long calendarRoomIdx);
}