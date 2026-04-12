package edu.icet.controller;

import edu.icet.model.dto.Booking;
import edu.icet.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
@CrossOrigin
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<String> createBooking(@RequestBody Booking bookingDTO) {
        bookingService.createBooking(bookingDTO);
        return ResponseEntity.ok("Booking Successful!");
    }

    @GetMapping("/get-all")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
