package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.TrainDTO;

import java.util.List;

public interface TrainService {

    List<TrainDTO> getAllTrainsDTO();

    TrainDTO getTrainDTOById(int id);

    TrainDTO findTrainByName(String trainName);

    void addTrain(TrainDTO trainDTO);

    void updateTrain(TrainDTO trainDTO);

}
