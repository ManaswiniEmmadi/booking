package com.example.eventbooking.model;



import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class Booking {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private String userName;
    private String mobile;
    private String email;
    private String seatType; //VIP, General
    private Integer numberOfSeats;

    @ElementCollection
    private List<String> selectedSeats;

    private String eventId;
    
    private String status; //"pending", "confirmed", "cancelled"
}
