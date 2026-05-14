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

    // Konstruktor-Injektion
    public ReportController(ExcelReportService excelReportService) {
        this.excelReportService = excelReportService;
    }

    // Pfad für Laravel / Admin Dashboard
    @GetMapping("/api/admin/export-bookings")
    public ResponseEntity<byte[]> exportBookings() {
        try {
            // Excel-Datei generieren
            byte[] excelData = excelReportService.generateExcelReport();

            // Response zurückgeben
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