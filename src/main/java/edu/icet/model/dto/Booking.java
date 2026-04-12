package edu.icet.model.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Booking {
    private Integer bookingId;
    private Long userId;
    private Integer carId;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private Double totalPrice;
    private String status;
}
