package com.booking.reportservice.controller;

import com.booking.reportservice.service.ExcelReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportController {

    private final ExcelReportService excelReportService;

    public ReportController(ExcelReportService excelReportService) {
        this.excelReportService = excelReportService;
    }

    // Excel-Download
    @GetMapping("/report/excel")
    public void downloadExcel(
            @RequestHeader("Authorization") String authHeader,
            HttpServletResponse response) throws Exception {

        // Bearer Token aus Header extrahieren
        String token = authHeader.replace("Bearer ", "");
        byte[] excelData = excelReportService.generateExcelReport(token);

        response.setContentType(
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        );
        response.setHeader(
            "Content-Disposition", "attachment; filename=bookings.xlsx"
        );

        response.getOutputStream().write(excelData);
        response.getOutputStream().flush();
    }
}