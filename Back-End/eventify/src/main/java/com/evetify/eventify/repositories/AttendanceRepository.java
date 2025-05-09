package com.evetify.eventify.repositories;

import com.evetify.eventify.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    ArrayList<Attendance> findByUser(Long userId);
}
