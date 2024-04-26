package uz.pdp.railwayticketproject.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.pdp.railwayticketproject.dto.auth.SignUpDto;
import uz.pdp.railwayticketproject.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User dtoToEntity(SignUpDto signUpDto);
}
