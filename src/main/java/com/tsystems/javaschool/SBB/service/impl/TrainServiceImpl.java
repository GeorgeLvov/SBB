package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.entities.Train;
import com.tsystems.javaschool.SBB.mapper.interfaces.TrainMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.TrainRepository;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private TrainMapper trainMapper;


    @Override
    @Transactional
    public List<TrainDTO> getAllTrainsDTO() {
        List<Train> trains = trainRepository.getAllTrains();
        return trainMapper.toDTOList(trains);
    }

    @Override
    @Transactional
    public TrainDTO getTrainDTOById(int id) {
        Train train = trainRepository.getTrainById(id);
        return trainMapper.toDTO(train);
    }

    @Override
    @Transactional
    public TrainDTO findTrainByName(String trainName) {
        Train train = trainRepository.findTrainByName(trainName);
        return trainMapper.toDTO(train);
    }

    @Override
    @Transactional
    public void addTrain(TrainDTO trainDTO) {
        Train train = trainMapper.toEntity(trainDTO);
        trainRepository.add(train);
    }

    @Override
    @Transactional
    public void updateTrain(TrainDTO trainDTO) {
        Train train = trainMapper.toEntity(trainDTO);
        trainRepository.update(train);
    }

}
