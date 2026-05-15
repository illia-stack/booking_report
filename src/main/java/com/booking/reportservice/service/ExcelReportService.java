package com.booking.reportservice.service;

import com.booking.reportservice.model.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelReportService {

    private final BookingService bookingService;

    public ExcelReportService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public byte[] generateExcelReport(String userToken) throws Exception {
        // Buchungen für den User abrufen (RLS)
        List<Booking> bookings = bookingService.getBookings(userToken);

        // Excel-Export wie bisher
        try (var workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook();
             var out = new java.io.ByteArrayOutputStream()) {

            var sheet = workbook.createSheet("Bookings");

            String[] headers = {
                    "ID","User ID","Property ID","Check In","Check Out",
                    "Total Price","Status","Created At","Updated At",
                    "Stripe Session ID","Stripe Payment Intent ID","Payed At"
            };

            var headerRow = sheet.createRow(0);
            var headerStyle = workbook.createCellStyle();
            var font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            headerStyle.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);

            for (int i=0; i<headers.length; i++){
                var cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            int rowIdx = 1;
            for (Booking b : bookings) {
                var row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(b.getId() != null ? b.getId() : 0);
                row.createCell(1).setCellValue(b.getUserId() != null ? b.getUserId() : 0);
                row.createCell(2).setCellValue(b.getPropertyId() != null ? b.getPropertyId() : 0);
                row.createCell(3).setCellValue(b.getCheckIn() != null ? b.getCheckIn().toString() : "");
                row.createCell(4).setCellValue(b.getCheckOut() != null ? b.getCheckOut().toString() : "");
                row.createCell(5).setCellValue(b.getTotalPrice() != null ? b.getTotalPrice() : 0.0);
                row.createCell(6).setCellValue(b.getStatus() != null ? b.getStatus() : "");
                row.createCell(7).setCellValue(b.getCreatedAt() != null ? b.getCreatedAt().toString() : "");
                row.createCell(8).setCellValue(b.getUpdatedAt() != null ? b.getUpdatedAt().toString() : "");
                row.createCell(9).setCellValue(b.getStripeSessionId() != null ? b.getStripeSessionId() : "");
                row.createCell(10).setCellValue(b.getStripePaymentIntentId() != null ? b.getStripePaymentIntentId() : "");
                row.createCell(11).setCellValue(b.getPayedAt() != null ? b.getPayedAt().toString() : "");
            }

            for (int i = 0; i < headers.length; i++) sheet.autoSizeColumn(i);
            workbook.write(out);
            return out.toByteArray();
        }
    }
}