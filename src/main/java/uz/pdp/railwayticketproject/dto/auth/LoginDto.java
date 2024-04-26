package uz.pdp.railwayticketproject.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record LoginDto(
        @Size(min = 6)
        @NotBlank
        String username,

        @Size(min = 8, max = 24)
        @NotBlank
        String password
){
}