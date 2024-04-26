package uz.pdp.railwayticketproject.dto.booking;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto{

    private Long userId;
    private Long trainId;

}
