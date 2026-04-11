package edu.icet.service.impl;

import edu.icet.model.dto.Car;
import edu.icet.model.entity.CarEntity;
import edu.icet.repository.CarRepository;
import edu.icet.service.CarService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Car> getAllCars() {

        List<CarEntity> entities = carRepository.findAll();


        return entities.stream()
                .map(entity -> modelMapper.map(entity, Car.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addCar(Car carDTO) {

        carRepository.save(modelMapper.map(carDTO, CarEntity.class));
    }

    @Override
    public void deleteCar(Integer id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car getCarById(Integer id) {
        CarEntity entity = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found!"));
        return modelMapper.map(entity, Car.class);
    }

    @Override
    public void updateCar(Car carDTO) {

        if (carRepository.existsById(carDTO.getCarId())) {

            carRepository.save(modelMapper.map(carDTO, CarEntity.class));
        } else {
            throw new RuntimeException("Car not found to update!");
        }
    }
}
