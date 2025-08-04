package com.example.eventbooking.controller;

import com.example.eventbooking.dto.BookingRequestDTO;
import com.example.eventbooking.dto.BookingResponseDTO;
import com.example.eventbooking.dto.BookingStatusUpdateDTO;
import com.example.eventbooking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/addBooking")
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody BookingRequestDTO dto) {
        return new ResponseEntity<>(bookingService.createBooking(dto), HttpStatus.CREATED);
    }

    @GetMapping("/viewAllBookings")
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long id) {
        return new ResponseEntity<>(bookingService.getBookingById(id), HttpStatus.OK);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByEventId(@PathVariable String eventId) {
        return new ResponseEntity<>(bookingService.getBookingsByEventId(eventId), HttpStatus.OK);
    }
    
    @GetMapping("/by-status")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByStatus(@RequestParam String status) {
        return new ResponseEntity<>(bookingService.getBookingsByStatus(status), HttpStatus.OK);
    }

    @PutMapping("/updateBooking/{id}")
    public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable Long id, @Valid @RequestBody BookingRequestDTO dto) {
        return new ResponseEntity<>(bookingService.updateBooking(id, dto), HttpStatus.OK);
    }
    
    @PatchMapping("/update-status/{id}")
    public ResponseEntity<String> updateBookingStatus(@PathVariable Long id, @RequestBody BookingStatusUpdateDTO statusDTO) {
        bookingService.updateBookingStatus(id, statusDTO.getStatus());
        return new ResponseEntity<>("Booking status updated successfully", HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookingById(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
        return new ResponseEntity<>("Booking deleted successfully", HttpStatus.NO_CONTENT);
    }
}
