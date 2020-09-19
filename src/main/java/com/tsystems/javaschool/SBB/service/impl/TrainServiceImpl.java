package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.entities.TrainEntity;
import com.tsystems.javaschool.SBB.repository.TrainRepository;
import com.tsystems.javaschool.SBB.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    TrainRepository trainRepository;

    @Override
    @Transactional
    public List<TrainEntity> getAllTrains() {
        return trainRepository.getAllTrains();
    }

    @Override
    @Transactional
    public TrainEntity getTrainById(int id) {
        return trainRepository.getTrainById(id);
    }

    @Override
    @Transactional
    public void add(TrainEntity trainEntity) {
        trainRepository.add(trainEntity);
    }

    @Override
    @Transactional
    public void update(TrainEntity trainEntity) {
        trainRepository.update(trainEntity);
    }

    @Override
    @Transactional
    public void delete(TrainEntity trainEntity) {
        trainRepository.delete(trainEntity);
    }
}
