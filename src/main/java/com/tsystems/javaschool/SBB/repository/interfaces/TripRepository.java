package com.tsystems.javaschool.SBB.repository.interfaces;

import com.tsystems.javaschool.SBB.entities.Trip;

import java.util.List;

public interface TripRepository {

    Trip getTripById(int id);

    void add(Trip trip);

    List<Trip> getTripsByTrainId(int trainId);

    List<Trip> getAllTrips();

    void cancelTrip(int tripId);

    void updateDepartureAndArrivalTimes(int id, int delay, String delayStr);
}
