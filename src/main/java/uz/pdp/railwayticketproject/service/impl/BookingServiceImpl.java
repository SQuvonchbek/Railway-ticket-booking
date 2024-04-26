package uz.pdp.railwayticketproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.railwayticketproject.dto.booking.BookingDto;
import uz.pdp.railwayticketproject.dto.booking.TrainSaveDto;
import uz.pdp.railwayticketproject.entity.Booking;
import uz.pdp.railwayticketproject.entity.Train;
import uz.pdp.railwayticketproject.entity.User;
import uz.pdp.railwayticketproject.mapstruct.TrainMapper;
import uz.pdp.railwayticketproject.repository.BookingRepository;
import uz.pdp.railwayticketproject.repository.TrainRepository;
import uz.pdp.railwayticketproject.repository.UserRepository;
import uz.pdp.railwayticketproject.service.BookingService;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final TrainRepository trainRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;


    @Override
    public Booking saveBooking(BookingDto booking) {
        User user = userRepository.findById(booking.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + booking.getUserId()));

        Train train = trainRepository.findById(booking.getTrainId())
                .orElseThrow(() -> new RuntimeException("Train not found with id: " + booking.getTrainId()));

        Booking build = Booking.builder()
                .user(user)
                .train(train)
                .build();

        return bookingRepository.save(build);
    }

    @Override
    public void cancelBooking(Long userId) {
        Booking booking = bookingRepository.findBookingByUserId(userId);
        bookingRepository.delete(booking);
    }


}
