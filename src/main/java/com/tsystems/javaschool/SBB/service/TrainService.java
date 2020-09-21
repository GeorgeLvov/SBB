package com.tsystems.javaschool.SBB.service;

import com.tsystems.javaschool.SBB.entities.Train;

import java.util.List;

public interface TrainService {

    List<Train> getAllTrains();

    Train getTrainById(int id);

    void add(Train train);

    void update(Train train);

    void delete(Train train);
}
