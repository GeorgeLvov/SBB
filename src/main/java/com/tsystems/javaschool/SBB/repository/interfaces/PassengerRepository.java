package com.tsystems.javaschool.SBB.repository.interfaces;

import com.tsystems.javaschool.SBB.entities.Passenger;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


public interface PassengerRepository {

    Passenger getPassengerById(int id);

    List<Object[]> getPassengerWithTicketsByPersonalData(String firstName, String lastName, LocalDate birthDate);

    Passenger getPassengerByPersonalData(String firstName, String lastName, LocalDate birthDate);

    List<Object[]> getAllPassengersByTrainIdAndTripId(int trainId, int tripId);

    void add(Passenger passenger);

}
