package com.booking.reportservice.repository;

import com.booking.reportservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Hier kannst du bei Bedarf eigene Queries hinzufügen
}