package uz.pdp.railwayticketproject.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String path;
    private String errorCode;
    private String errorMessage;
    private int status;

    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();
}

