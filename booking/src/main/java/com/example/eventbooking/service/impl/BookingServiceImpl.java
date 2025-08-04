package com.example.eventbooking.service.impl;

import com.example.eventbooking.dto.*;
import com.example.eventbooking.exception.BookingNotFoundException;
import com.example.eventbooking.model.Booking;
import com.example.eventbooking.repository.BookingRepository;
import com.example.eventbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    private BookingResponseDTO toDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setBookingId(booking.getBookingId());
        dto.setUserName(booking.getUserName());
        dto.setMobile(booking.getMobile());
        dto.setEmail(booking.getEmail());
        dto.setSeatType(booking.getSeatType());
        dto.setNumberOfSeats(booking.getNumberOfSeats());
        dto.setSelectedSeats(booking.getSelectedSeats());
        dto.setEventId(booking.getEventId());
        dto.setStatus(booking.getStatus());
        return dto;
    }

    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO dto) {
        Booking booking = new Booking(null, dto.getUserName(), dto.getMobile(), dto.getEmail(),
                dto.getSeatType(), dto.getNumberOfSeats(), dto.getSelectedSeats(), dto.getEventId(), dto.getStatus());
        return toDTO(bookingRepository.save(booking));
    }

    @Override
    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll()
        		.stream()
        		.map(this::toDTO)
        		.collect(Collectors.toList());
    }

    @Override
    public BookingResponseDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking with ID " + id + " not found"));
        return toDTO(booking);
    }

    @Override
    public List<BookingResponseDTO> getBookingsByEventId(String eventId) {
        return bookingRepository.findByEventId(eventId)
        		.stream()
        		.map(this::toDTO)
        		.collect(Collectors.toList());
    }
    
    @Override
    public List<BookingResponseDTO> getBookingsByStatus(String status) {
        return bookingRepository.findByStatusIgnoreCase(status)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }



    @Override
    public BookingResponseDTO updateBooking(Long id, BookingRequestDTO dto) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking with ID " + id + " not found"));
        booking.setUserName(dto.getUserName());
        booking.setMobile(dto.getMobile());
        booking.setEmail(dto.getEmail());
        booking.setSeatType(dto.getSeatType());
        booking.setNumberOfSeats(dto.getNumberOfSeats());
        booking.setSelectedSeats(dto.getSelectedSeats());
        booking.setEventId(dto.getEventId());
        booking.setStatus(dto.getStatus());
        return toDTO(bookingRepository.save(booking));
    }

    @Override
    public void deleteBookingById(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new BookingNotFoundException("Cannot delete. Booking with ID " + id + " not found");
        }
        bookingRepository.deleteById(id);
    }
    
    @Override
    public void updateBookingStatus(Long id, String status) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));
        
        booking.setStatus(status);
        bookingRepository.save(booking);
    }
    


}
