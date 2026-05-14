package com.booking.reportservice.service;

import com.booking.reportservice.model.Booking;
import com.booking.reportservice.repository.BookingRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ExcelReportService {

    private final BookingRepository bookingRepository;

    public ExcelReportService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public byte[] generateExcelReport() throws Exception {
        // Alle Buchungen aus der DB holen
        List<Booking> bookings = bookingRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Bookings");

            // Header-Array
            String[] headers = {
                    "ID",
                    "User ID",
                    "Property ID",
                    "Check In",
                    "Check Out",
                    "Total Price",
                    "Status",
                    "Created At",
                    "Updated At",
                    "Stripe Session ID",
                    "Stripe Payment Intent ID",
                    "Payed At"
            };

            // Header-Zeile erstellen
            Row headerRow = sheet.createRow(0);

            // Header Style: fett + zentriert
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Datenzeilen
            int rowIdx = 1;
            for (Booking b : bookings) {
                Row row = sheet.createRow(rowIdx++);
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

            // Spaltenbreite automatisch anpassen
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Excel in ByteArray ausgeben
            workbook.write(out);
            System.out.println("Exported " + bookings.size() + " bookings to Excel");
            return out.toByteArray();
        }
    }
}