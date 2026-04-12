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
    @Transactional // වැදගත්: එක තැනක error එකක් ආවොත් ඔක්කොම rollback වෙනවා
    public void createBooking(Booking bookingDTO) {
        // 1. User සහ Car හොයාගන්නවා
        UserEntity user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        CarEntity car = carRepository.findById(bookingDTO.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        // 2. දින ගණන බලලා Total Price එක හදනවා
        long days = ChronoUnit.DAYS.between(bookingDTO.getPickupDate(), bookingDTO.getReturnDate());
        if (days <= 0) days = 1; // අවම දින 1ක් ලෙස සලකමු

        Double total = days * car.getDailyRate();

        // 3. BookingEntity එක සකස් කරනවා
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setUser(user);
        bookingEntity.setCar(car);
        bookingEntity.setPickupDate(bookingDTO.getPickupDate());
        bookingEntity.setReturnDate(bookingDTO.getReturnDate());
        bookingEntity.setTotalPrice(total);
        bookingEntity.setStatus("Confirmed");

        // 4. කාර් එකේ Availability එක FALSE කරනවා
        car.setIsAvailable(false);
        carRepository.save(car);

        // 5. බුකින් එක සේව් කරනවා
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
}