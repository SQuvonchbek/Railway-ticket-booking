package uz.pdp.railwayticketproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.railwayticketproject.dto.booking.TrainSaveDto;
import uz.pdp.railwayticketproject.entity.Train;
import uz.pdp.railwayticketproject.mapstruct.TrainMapper;
import uz.pdp.railwayticketproject.repository.TrainRepository;
import uz.pdp.railwayticketproject.service.TrainService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;

    @Override
    public Train saveTrain(TrainSaveDto trainSaveDto) {
        if (trainRepository.existsByNameAndStatusTrue(trainSaveDto.getName())) {
            throw new RuntimeException("This train already exists");
        }

        Train train = TrainMapper.INSTANCE.dtoToEntity(trainSaveDto);
        return trainRepository.save(train);
    }

    @Override
    public List<Train> showTrains() {
        return trainRepository.findAll();
    }

    @Override
    public List<Train> searchTrain(String search) {
        return trainRepository.findAllByName(search);
    }
}
