package com.booking.reportservice.controller;

import com.booking.reportservice.service.XmlReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final XmlReportService xmlReportService;

    public ReportController(XmlReportService xmlReportService) {
        this.xmlReportService = xmlReportService;
    }

    @GetMapping("/bookings")
    public ResponseEntity<String> generateReport() throws Exception {

        String xml = xmlReportService.generateXmlReport();

        return ResponseEntity.ok()
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=booking-report.xml"
            )
            .contentType(MediaType.APPLICATION_XML)
            .body(xml);
    }
}