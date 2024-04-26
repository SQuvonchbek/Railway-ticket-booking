package uz.pdp.railwayticketproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.railwayticketproject.entity.Booking;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Booking findBookingByUserId(Long userId);

}
