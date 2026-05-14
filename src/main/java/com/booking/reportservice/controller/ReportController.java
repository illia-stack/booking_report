package com.booking.reportservice.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelReportController {

    private final ExcelReportService excelReportService;

    public ExcelReportController(ExcelReportService excelReportService) {
        this.excelReportService = excelReportService;
    }

    @GetMapping("/api/reports/bookings")
    public ResponseEntity<byte[]> exportBookings() {
        try {
            byte[] excelData = excelReportService.generateExcelReport();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=booking-report.xlsx")
                    .contentType(MediaType.parseMediaType(
                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(excelData);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}