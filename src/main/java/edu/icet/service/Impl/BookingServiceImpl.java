package edu.icet.service.Impl;

import edu.icet.model.dto.Booking;
import edu.icet.model.entity.BookingEntity;
import edu.icet.model.entity.CarEntity;
import edu.icet.model.entity.UserEntity;
import edu.icet.repository.BookingRepository;
import edu.icet.repository.CarRepository;
import edu.icet.repository.UserRepository;
import edu.icet.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void createBooking(Booking bookingDTO) {

        UserEntity user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        CarEntity car = carRepository.findById(bookingDTO.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));


        long days = ChronoUnit.DAYS.between(bookingDTO.getPickupDate(), bookingDTO.getReturnDate());
        if (days <= 0) days = 1;

        Double total = days * car.getDailyRate();


        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setUser(user);
        bookingEntity.setCar(car);
        bookingEntity.setPickupDate(bookingDTO.getPickupDate());
        bookingEntity.setReturnDate(bookingDTO.getReturnDate());
        bookingEntity.setTotalPrice(total);
        bookingEntity.setStatus("Confirmed");


        car.setIsAvailable(false);
        carRepository.save(car);


        bookingRepository.save(bookingEntity);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(entity -> {
                    Booking dto = modelMapper.map(entity, Booking.class);
                    dto.setUserId(entity.getUser().getUserId());
                    dto.setCarId(entity.getCar().getCarId());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void returnCar(Integer bookingId) {

        BookingEntity booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));


        booking.setStatus("Completed");


        CarEntity car = booking.getCar();
        car.setIsAvailable(true);


        carRepository.save(car);
        bookingRepository.save(booking);
    }
}