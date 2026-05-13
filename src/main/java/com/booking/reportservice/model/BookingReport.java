package com.booking.reportservice.model;

import java.util.List;

public class BookingReport {

private List<Booking> bookings;

public BookingReport() {
}

public BookingReport(List<Booking> bookings) {
    this.bookings = bookings;
}

public List<Booking> getBookings() {
    return bookings;
}

public void setBookings(List<Booking> bookings) {
    this.bookings = bookings;
}

}