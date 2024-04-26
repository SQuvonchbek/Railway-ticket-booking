package uz.pdp.railwayticketproject.dto.booking;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainSaveDto{
        private String name;
        private Integer seats;
        private Integer countOfWagons;
        private String fare;
        private String fromPlace;
        private String toPlace;
}