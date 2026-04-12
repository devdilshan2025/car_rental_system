package edu.icet.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private Integer carId;
    private String brand;
    private String model;
    private String carType;
    private Integer seatingCapacity;
    private Double dailyRate;
    private String transmission;
    private String fuelType;
    @JsonProperty("isAvailable")
    private Boolean isAvailable;
}
