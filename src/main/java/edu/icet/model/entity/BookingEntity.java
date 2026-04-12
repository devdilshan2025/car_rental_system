package edu.icet.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    @ManyToOne // බොහෝ බුකින්ස් එක් යූසර් කෙනෙකුට තිබිය හැක
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne // බොහෝ බුකින්ස් එක් කාර් එකකට තිබිය හැක
    @JoinColumn(name = "car_id", nullable = false)
    private CarEntity car;

    @Column(name = "pickup_date", nullable = false)
    private LocalDate pickupDate;

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    private String status = "Pending";
}