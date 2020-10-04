package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.entities.Train;
import com.tsystems.javaschool.SBB.mapper.TrainMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.TrainRepository;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    TrainRepository trainRepository;
    @Autowired
    TrainMapper trainMapper;


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
    public TrainDTO findTrainByTrainName(String trainName) {
        Train train = trainRepository.findTrainByTrainName(trainName);
        return trainMapper.toDTO(train);
    }

    @Override
    @Transactional
    public void add(TrainDTO trainDTO) {
        Train train = trainMapper.toEntity(trainDTO);
        trainRepository.add(train);
    }

    @Override
    @Transactional
    public void update(TrainDTO trainDTO) {
        Train train = trainMapper.toEntity(trainDTO);
        trainRepository.update(train);
    }

    @Override
    @Transactional
    public void delete(TrainDTO trainDTO) {
        Train train = trainMapper.toEntity(trainDTO);
        trainRepository.delete(train);
    }
}
