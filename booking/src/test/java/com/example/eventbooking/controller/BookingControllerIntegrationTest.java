package com.example.eventbooking.controller;

import com.example.eventbooking.dto.BookingRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAndGetBooking_flow() throws Exception {
        BookingRequestDTO request = new BookingRequestDTO();
        request.setUserName("Bob");
        request.setMobile("9876543210");
        request.setEmail("bob@example.com");
        request.setSeatType("General");
        request.setNumberOfSeats(1);
        request.setSelectedSeats(List.of("C3"));
        request.setEventId("EVT1");
        request.setStatus("PENDING");

        // Create
        String res = mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName").value("Bob"))
                .andReturn().getResponse().getContentAsString();

        long bookingId = objectMapper.readTree(res).get("bookingId").asLong();

        // Fetch by ID
        mockMvc.perform(get("/api/bookings/{id}", bookingId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value(bookingId))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }
}
