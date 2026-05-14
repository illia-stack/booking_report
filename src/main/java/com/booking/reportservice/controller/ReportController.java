package com.booking.reportservice.controller;

import com.booking.reportservice.service.XmlReportService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final XmlReportService xmlReportService;

    public ReportController(
        XmlReportService xmlReportService
    ) {
        this.xmlReportService = xmlReportService;
    }

    @GetMapping("/bookings")
    public ResponseEntity<byte[]> generateReport()
        throws Exception {

        String filePath =
            xmlReportService.generateXmlReport();

        byte[] xmlContent =
            Files.readAllBytes(
                Paths.get(filePath)
            );

        return ResponseEntity.ok()
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=booking-report.xml"
            )
            .contentType(MediaType.APPLICATION_XML)
            .body(xmlContent);
    }
}