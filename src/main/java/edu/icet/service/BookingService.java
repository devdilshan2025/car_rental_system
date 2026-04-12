package edu.icet.service;

import edu.icet.model.dto.Booking;

import java.util.List;

public interface BookingService {
    void createBooking(Booking bookingDTO);
    List<Booking> getAllBookings();
    void returnCar(Integer bookingId);
}
