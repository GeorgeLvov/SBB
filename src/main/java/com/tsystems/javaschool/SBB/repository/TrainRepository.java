package com.tsystems.javaschool.SBB.repository;

import com.tsystems.javaschool.SBB.entities.Train;

import java.util.List;

public interface TrainRepository {

    List<Train> getAllTrains();

    Train getTrainById(int id);

    void add(Train train);

    void update(Train train);

    void delete(Train train);
}
