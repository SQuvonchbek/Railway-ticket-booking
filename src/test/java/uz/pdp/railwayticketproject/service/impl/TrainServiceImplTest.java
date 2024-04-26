package uz.pdp.railwayticketproject.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uz.pdp.railwayticketproject.dto.booking.TrainSaveDto;
import uz.pdp.railwayticketproject.entity.Train;
import uz.pdp.railwayticketproject.repository.TrainRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class TrainServiceImplTest {

    TrainServiceImpl trainServiceImpl;
    TrainRepository trainRepository;

    @BeforeEach
    void setUp() {
        trainRepository = Mockito.mock(TrainRepository.class);
        trainServiceImpl = new TrainServiceImpl(trainRepository);
    }

    @Test
    void saveTrain() {
        TrainSaveDto train = TrainSaveDto.builder()
                .name("train1")
                .seats(200)
                .countOfWagons(5)
                .fare("200000")
                .fromPlace("tashkent")
                .toPlace("samarkand")
                .build();

        when(trainRepository.save(new Train())).thenReturn(new Train());
        assertDoesNotThrow(() -> trainServiceImpl.saveTrain(train));
        verify(trainRepository, Mockito.times(1)).save(any(Train.class));

    }

    @Test
    void showTrains() {

        trainServiceImpl.showTrains();
    }

    @Test
    void searchTrain() {
        trainServiceImpl.searchTrain("tashkent");
    }
}