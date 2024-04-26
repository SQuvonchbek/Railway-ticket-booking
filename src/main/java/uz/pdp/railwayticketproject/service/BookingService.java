package uz.pdp.railwayticketproject.service;

import uz.pdp.railwayticketproject.dto.booking.BookingDto;
import uz.pdp.railwayticketproject.entity.Booking;

public interface BookingService {


    Booking saveBooking(BookingDto booking);

    void cancelBooking(Long userId);

}
