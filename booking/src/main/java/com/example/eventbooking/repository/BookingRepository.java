package com.example.eventbooking.repository;

import com.example.eventbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByEventId(String eventId);
    
    List<Booking> findByStatusIgnoreCase(String status);

}
