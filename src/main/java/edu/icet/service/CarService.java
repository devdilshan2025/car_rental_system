package edu.icet.service;

import edu.icet.model.dto.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    void addCar(Car carDTO);
    void deleteCar(Integer id);
    Car getCarById(Integer id);
    void updateCar(Car carDTO);
}