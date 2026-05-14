package com.booking.reportservice.service;

import com.booking.reportservice.model.Booking;
import com.booking.reportservice.model.BookingReport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XmlReportService {

    private final BookingService bookingService;

    public XmlReportService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookingReport generateBookingReport() {
        List<Booking> bookings = bookingService.getAllBookings();
        return new BookingReport(bookings);
    }
}