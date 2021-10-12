package com.example.backend.domain.calendar;

import com.example.backend.entity.calendar.CalendarRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<CalendarRoom, Long> {
}