package com.tsystems.javaschool.SBB.repository.interfaces;

import com.tsystems.javaschool.SBB.entities.Passenger;

import java.sql.Date;
import java.util.List;


public interface PassengerRepository {

    List<Passenger> getAllPassengers();

    Passenger getPassengerById(int id);

    List<Object[]> getPassengerWithTicketsByFields(String firstName, String lastName, Date birthDate);

    Passenger findPassengerByPersonalData(String firstName, String lastName, Date birthDate);

    List<Object[]> getAllPassengersByTrainIdAndTripId(int trainId, int tripId);

    void add(Passenger passenger);

    void update(Passenger passenger);

    void delete(Passenger passenger);
}
