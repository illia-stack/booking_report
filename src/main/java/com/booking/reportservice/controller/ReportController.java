package com.booking.reportservice.controller;

import com.booking.reportservice.service.ExcelReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    private final ExcelReportService excelReportService;

    public ReportController(ExcelReportService excelReportService) {
        this.excelReportService = excelReportService;
    }

    @GetMapping("/api/reports/bookings")
    public ResponseEntity<byte[]> exportBookings() throws Exception {
        byte[] bytes = excelReportService.generateExcelReport();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=booking-report.xlsx")
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(bytes);
    }
}