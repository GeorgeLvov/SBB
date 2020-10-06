package com.tsystems.javaschool.SBB.repository.interfaces;

import com.tsystems.javaschool.SBB.entities.Trip;

import java.util.List;

public interface TripRepository {

    List<Trip> getAllTrips();

    Trip getTripById(int id);

    void addTrip(Trip trip);
}
