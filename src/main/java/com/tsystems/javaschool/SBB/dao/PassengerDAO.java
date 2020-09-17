package com.tsystems.javaschool.SBB.dao;

import com.tsystems.javaschool.SBB.entities.Passenger;

import java.util.List;

public interface PassengerDAO {

    List<Passenger> getAllPassengers();

    Passenger getPassengerById(int id);

    void add(Passenger passenger);

    void update(Passenger passenger);

    void delete(Passenger passenger);
}
