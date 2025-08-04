package com.example.eventbooking.service.impl;

import com.example.eventbooking.dto.BookingRequestDTO;
import com.example.eventbooking.exception.BookingNotFoundException;
import com.example.eventbooking.model.Booking;
import com.example.eventbooking.repository.BookingRepository;
import com.example.eventbooking.dto.BookingResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBooking_success() {
        BookingRequestDTO request = new BookingRequestDTO();
        request.setUserName("TestUser");
        request.setMobile("1234567890");
        request.setEmail("test@example.com");
        request.setSeatType("VIP");
        request.setNumberOfSeats(2);
        request.setSelectedSeats(List.of("A1", "A2"));
        request.setEventId("E1");
        request.setStatus("PENDING");

        Booking saved = new Booking(null, "TestUser", "1234567890", "test@example.com",
                "VIP", 2, List.of("A1", "A2"), "E1", "PENDING");
        saved.setBookingId(1L);

        when(bookingRepository.save(any(Booking.class))).thenReturn(saved);

        BookingResponseDTO response = bookingService.createBooking(request);

        assertNotNull(response);
        assertEquals(1L, response.getBookingId());
        assertEquals("TestUser", response.getUserName());
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    void getBookingById_notFound_throws() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookingNotFoundException.class, () -> bookingService.getBookingById(1L));
    }

    @Test
    void getBookingsByStatus_returnsList() {
        Booking b = new Booking(1L, "Alice", "1111111111", "a@a.com", "General",
                1, List.of("B1"), "E1", "CONFIRMED");
        when(bookingRepository.findByStatusIgnoreCase("CONFIRMED")).thenReturn(List.of(b));

        var list = bookingService.getBookingsByStatus("CONFIRMED");
        assertEquals(1, list.size());
        assertEquals("Alice", list.get(0).getUserName());
    }
}
