package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.entities.Train;
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
    public List<Train> getAllTrains() {
        return trainRepository.getAllTrains();
    }

    @Override
    @Transactional
    public Train getTrainById(int id) {
        return trainRepository.getTrainById(id);
    }

    @Override
    @Transactional
    public void add(Train train) {
        trainRepository.add(train);
    }

    @Override
    @Transactional
    public void update(Train train) {
        trainRepository.update(train);
    }

    @Override
    @Transactional
    public void delete(Train train) {
        trainRepository.delete(train);
    }
}
