package com.example.eventbooking.service;

import com.example.eventbooking.dto.BookingRequestDTO;
import com.example.eventbooking.dto.BookingResponseDTO;

import java.util.List;

public interface BookingService {
    BookingResponseDTO createBooking(BookingRequestDTO dto);
    List<BookingResponseDTO> getAllBookings();
    BookingResponseDTO getBookingById(Long id);
    List<BookingResponseDTO> getBookingsByEventId(String eventId);
    BookingResponseDTO updateBooking(Long id, BookingRequestDTO dto);
    void deleteBookingById(Long id);
	void updateBookingStatus(Long id, String status);
	List<BookingResponseDTO> getBookingsByStatus(String status);

}
