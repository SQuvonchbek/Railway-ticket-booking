package uz.pdp.railwayticketproject.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateDto(
        Long id,

        @Size(min = 6)
        @NotBlank
        String username,

        @Email
        @NotBlank
        String email,

        @Size(min = 8, max = 24)
        @NotBlank String password,

        boolean enabled

){
}