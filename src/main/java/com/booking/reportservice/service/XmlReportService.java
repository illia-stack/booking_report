package com.booking.reportservice.service;

import com.booking.reportservice.model.BookingReport;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

import com.booking.reportservice.model.Booking;

@Service
public class XmlReportService {

    private final BookingService bookingService;

    public XmlReportService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public String generateXmlReport() throws Exception {

        List<Booking> bookings =
            bookingService.getAllBookings();

        BookingReport report =
            new BookingReport(bookings);

        XmlMapper xmlMapper = new XmlMapper();

        String filePath =
            "booking-report.xml";

        xmlMapper.writeValue(
            new File(filePath),
            report
        );

        return filePath;
    }
}