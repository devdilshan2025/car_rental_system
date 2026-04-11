package edu.icet.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Integer carId;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(name = "car_type")
    private String carType;

    @Column(name = "seating_capacity")
    private Integer seatingCapacity;

    @Column(name = "daily_rate", nullable = false)
    private Double dailyRate;

    private String transmission;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "is_available")
    private Boolean isAvailable = true;
}
