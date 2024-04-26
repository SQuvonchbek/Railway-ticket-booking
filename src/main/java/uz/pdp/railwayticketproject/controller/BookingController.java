package uz.pdp.railwayticketproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.railwayticketproject.dto.booking.BookingDto;
import uz.pdp.railwayticketproject.dto.booking.TrainSaveDto;
import uz.pdp.railwayticketproject.entity.Booking;
import uz.pdp.railwayticketproject.entity.Train;
import uz.pdp.railwayticketproject.service.BookingService;
import uz.pdp.railwayticketproject.service.TrainService;

import java.util.List;

@RestController
@RequestMapping("/v1/railway")
@RequiredArgsConstructor
public class BookingController {

    private final TrainService trainService;
    private final BookingService bookingService;

    @PostMapping("/save")
    public Train saveTrain(@RequestBody TrainSaveDto train) {
        return trainService.saveTrain(train);
    }

    @GetMapping
    public List<Train> getAllTrains() {
        return trainService.showTrains();
    }

    @GetMapping("/search")
    public List<Train> searchTrain(@RequestParam("search") String search) {
        return trainService.searchTrain(search);
    }

    @PostMapping("/booking")
    public Booking booking(@RequestBody BookingDto bookingDto) {
        return bookingService.saveBooking(bookingDto);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelBooking(@RequestParam("userId") Long userId) {
        bookingService.cancelBooking(userId);
        return ResponseEntity.ok("Booking cancelled");
    }

}
