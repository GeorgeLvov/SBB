package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.TrainDTO;

import java.util.List;

public interface TrainService {

    List<TrainDTO> getAllTrainsDTO();

    TrainDTO getTrainDTOById(int id);

    TrainDTO findTrainByTrainName(String trainName);

    void add(TrainDTO trainDTO);

    void update(TrainDTO trainDTO);

    void delete(TrainDTO trainDTO);
}
