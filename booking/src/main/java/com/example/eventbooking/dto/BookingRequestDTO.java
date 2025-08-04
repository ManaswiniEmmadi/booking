package com.example.eventbooking.dto;


import lombok.Data;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Data
public class BookingRequestDTO {
    @NotBlank
    private String userName;

    @Pattern(regexp = "\\d{10}", message = "Mobile must be 10 digits")
    private String mobile;

    @Email
    private String email;

    @NotBlank
    private String seatType;

    @Min(1)
    private Integer numberOfSeats;

    @NotEmpty
    private List<String> selectedSeats;

    @NotBlank
    private String eventId;
    
    @NotBlank(message = "Status is required")
    private String status;
}
