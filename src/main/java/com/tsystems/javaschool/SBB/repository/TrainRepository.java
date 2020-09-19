package com.tsystems.javaschool.SBB.repository;

import com.tsystems.javaschool.SBB.entities.TrainEntity;

import java.util.List;

public interface TrainRepository {

    List<TrainEntity> getAllTrains();

    TrainEntity getTrainById(int id);

    void add(TrainEntity trainEntity);

    void update(TrainEntity trainEntity);

    void delete(TrainEntity trainEntity);
}
