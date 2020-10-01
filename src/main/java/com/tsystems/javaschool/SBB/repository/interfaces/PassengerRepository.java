package com.tsystems.javaschool.SBB.repository.interfaces;

import com.tsystems.javaschool.SBB.entities.Passenger;

import java.util.List;


public interface PassengerRepository {

    List<Passenger> getAllPassengers();

    Passenger getPassengerById(int id);

    void add(Passenger passenger);

    void update(Passenger passenger);

    void delete(Passenger passenger);
}
