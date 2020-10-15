package com.tsystems.javaschool.SBB.repository.interfaces;

import com.tsystems.javaschool.SBB.entities.Train;

import java.util.List;

public interface TrainRepository {

    List<Train> getAllTrains();

    Train getTrainById(int id);

    Train findTrainByName(String trainName);

    void add(Train train);

    void update(Train train);

    void delete(Train train);
}
