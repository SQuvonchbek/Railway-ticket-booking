package uz.pdp.railwayticketproject.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetMailDto {

    private String to;
    private String subject;
    private String text;

}
