package com.booking.reportservice.controller;

import com.booking.reportservice.model.Booking;
import com.booking.reportservice.model.BookingReport;
import com.booking.reportservice.service.BookingService;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final BookingService bookingService;

    public ReportController(
        BookingService bookingService
    ) {
        this.bookingService = bookingService;
    }

    @GetMapping(
        value = "/bookings/xml",
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<String> generateReport()
        throws Exception {

        List<Booking> bookings =
            bookingService.getAllBookings();

        BookingReport report =
            new BookingReport(bookings);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.findAndRegisterModules();

        String xml =
            xmlMapper.writeValueAsString(report);

        return ResponseEntity.ok()
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=booking-report.xml"
            )
            .body(xml);
    }
}