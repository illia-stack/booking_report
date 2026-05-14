package com.booking.reportservice.service;

import com.booking.reportservice.model.Booking;
import com.booking.reportservice.model.BookingReport;
import com.booking.reportservice.repository.BookingRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XmlReportService {

    private final BookingService bookingService;

    public XmlReportService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public String generateXmlReport() throws Exception {

        List<Booking> bookings =
            bookingService.getAllBookings();

        BookingReport report =
            new BookingReport(bookings);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.findAndRegisterModules();

        return xmlMapper.writeValueAsString(report);
    }
}