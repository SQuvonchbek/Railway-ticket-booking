package uz.pdp.railwayticketproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "train")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private Integer seats;

    private Integer countOfWagons;

    private String fare;

    private String fromPlace;

    private String toPlace;

    @Builder.Default
    private Boolean status = Boolean.TRUE;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private Set<Booking> bookings = new HashSet<>();


}