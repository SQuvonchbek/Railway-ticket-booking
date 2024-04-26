package uz.pdp.railwayticketproject.service;

import uz.pdp.railwayticketproject.dto.booking.TrainSaveDto;
import uz.pdp.railwayticketproject.entity.Train;
import java.util.List;

public interface TrainService {

    Train saveTrain(TrainSaveDto trainSaveDto);
    List<Train> showTrains();
    List<Train> searchTrain(String search);

}
