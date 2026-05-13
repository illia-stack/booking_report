package com.booking.reportservice.controller;

import com.booking.reportservice.model.Booking;
import com.booking.reportservice.model.BookingReport;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

@PostMapping(
    value = "/bookings",
    produces = MediaType.APPLICATION_XML_VALUE
)
public ResponseEntity<String> generateReport(
    @RequestBody List<Booking> bookings
) throws Exception {

    XmlMapper xmlMapper = new XmlMapper();

    BookingReport report = new BookingReport(bookings);

    String xml = xmlMapper.writeValueAsString(report);

    return ResponseEntity.ok()
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=booking-report.xml"
            )
            .body(xml);
}

}