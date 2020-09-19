package com.tsystems.javaschool.SBB.repository;

import com.tsystems.javaschool.SBB.entities.PassengerEntity;

import java.util.List;


public interface PassengerRepository {

    List<PassengerEntity> getAllPassengers();

    PassengerEntity getPassengerById(int id);

    void add(PassengerEntity passengerEntity);

    void update(PassengerEntity passengerEntity);

    void delete(PassengerEntity passengerEntity);
}
