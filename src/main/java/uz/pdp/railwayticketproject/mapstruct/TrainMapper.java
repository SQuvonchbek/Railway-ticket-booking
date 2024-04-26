package uz.pdp.railwayticketproject.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.pdp.railwayticketproject.dto.booking.TrainSaveDto;
import uz.pdp.railwayticketproject.entity.Train;

@Mapper(componentModel = "spring")
public interface TrainMapper {

    TrainMapper INSTANCE = Mappers.getMapper(TrainMapper.class);

    Train dtoToEntity(TrainSaveDto trainSaveDto);

}
