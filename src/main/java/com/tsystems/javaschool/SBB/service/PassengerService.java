package com.tsystems.javaschool.SBB.service;

import com.tsystems.javaschool.SBB.entities.PassengerEntity;

import java.util.List;


public interface PassengerService {

    List<PassengerEntity> getAllPassengers();

    PassengerEntity getPassengerById(int id);

    void add(PassengerEntity passenger);

    void update(PassengerEntity passenger);

    void delete(PassengerEntity passenger);

}
