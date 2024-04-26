package uz.pdp.railwayticketproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Size(min = 6)
    private String username;

    @NotBlank
    @Email
    private String email;

    @Size(min = 8, max = 24)
    @NotBlank
    private String password;

    @Builder.Default
    private boolean enabled = Boolean.TRUE;

    @Builder.Default
    private LocalDateTime registeredTime = LocalDateTime.now();

    @ManyToMany
    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Booking> bookings = new HashSet<>();

}