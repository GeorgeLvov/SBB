package com.tsystems.javaschool.SBB.service;

import com.tsystems.javaschool.SBB.entities.TrainEntity;

import java.util.List;

public interface TrainService {

    List<TrainEntity> getAllTrains();

    TrainEntity getTrainById(int id);

    void add(TrainEntity trainEntity);

    void update(TrainEntity trainEntity);

    void delete(TrainEntity trainEntity);
}
