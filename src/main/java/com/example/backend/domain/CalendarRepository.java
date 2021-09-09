package com.example.backend.domain;

import com.example.backend.entity.CalendarRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<CalendarRoom, Long> {
}