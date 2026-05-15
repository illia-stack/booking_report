package com.booking.reportservice.service;

import com.booking.reportservice.model.Booking;
import com.booking.reportservice.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getBookings() {
        return bookingRepository.findAll(); // Alle Buchungen aus der DB
    }
}