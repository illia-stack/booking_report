package com.booking.reportservice.controller;

import com.booking.reportservice.service.ExcelReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    private final ExcelReportService excelReportService;

    public ReportController(ExcelReportService excelReportService) {
        this.excelReportService = excelReportService;
    }

    @GetMapping("/report/excel")
        public void downloadExcel(HttpServletResponse response) throws Exception {
            byte[] excelData = excelReportService.generateExcelReport();

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