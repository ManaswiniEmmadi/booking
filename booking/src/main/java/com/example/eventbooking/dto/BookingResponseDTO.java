package com.example.eventbooking.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingResponseDTO {
    private Long bookingId;
    private String userName;
    private String mobile;
    private String email;
    private String seatType;
    private Integer numberOfSeats;
    private List<String> selectedSeats;
    private String eventId;
    private String status;
}
