public String generateXmlReport() throws Exception {

    List<Booking> bookings =
        bookingService.getAllBookings();

    BookingReport report =
        new BookingReport(bookings);

    XmlMapper xmlMapper = new XmlMapper();

    xmlMapper.findAndRegisterModules();

    return xmlMapper.writeValueAsString(report);
}