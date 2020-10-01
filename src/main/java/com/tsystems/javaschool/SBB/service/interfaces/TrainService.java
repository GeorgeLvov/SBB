package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.entities.Train;

import java.util.List;

public interface TrainService {

    List<TrainDTO> getAllTrainsDTO();

    TrainDTO getTrainDTOById(int id);

    void add(TrainDTO trainDTO);

    void update(TrainDTO trainDTO);

    void delete(TrainDTO trainDTO);
}
