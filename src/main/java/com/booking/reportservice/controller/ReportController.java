package com.booking.reportservice.controller;

import com.booking.reportservice.model.BookingReport;
import com.booking.reportservice.service.XmlReportService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final XmlReportService xmlReportService;

    public ReportController(XmlReportService xmlReportService) {
        this.xmlReportService = xmlReportService;
    }

    @GetMapping(value = "/bookings", produces = MediaType.APPLICATION_XML_VALUE)
    public BookingReport generateReport() {
        return xmlReportService.generateBookingReport();
    }
}