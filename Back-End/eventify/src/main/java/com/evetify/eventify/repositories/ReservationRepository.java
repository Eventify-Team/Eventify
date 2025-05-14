package com.evetify.eventify.repositories;

import com.evetify.eventify.models.Event;
import com.evetify.eventify.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
