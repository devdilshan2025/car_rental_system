package edu.icet.controller;

import edu.icet.model.dto.Car;
import edu.icet.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CarController {

    private final CarService carService;


    @GetMapping("/get-all")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }


    @PostMapping("/add")
    public ResponseEntity<String> addCar(@RequestBody Car carDTO) {
        carService.addCar(carDTO);
        return ResponseEntity.ok("Car Added Successfully!");
    }


    @GetMapping("/get/{id}")
    public Car getCarById(@PathVariable Integer id) {
        return carService.getCarById(id);
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateCar(@RequestBody Car carDTO) {
        carService.updateCar(carDTO);
        return ResponseEntity.ok("Car Updated Successfully!");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Integer id) {
        carService.deleteCar(id);
        return ResponseEntity.ok("Car Deleted Successfully!");
    }
}