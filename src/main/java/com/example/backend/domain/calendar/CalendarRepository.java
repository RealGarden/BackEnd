package com.example.backend.domain.calendar;

import com.example.backend.entity.calendar.CalendarRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarRoom, Long> {
    List<CalendarRoom> findAllByMember(String id);

}