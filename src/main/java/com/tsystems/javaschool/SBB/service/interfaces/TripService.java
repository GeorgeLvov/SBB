package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.TripDTO;


import java.util.List;

public interface TripService {

    TripDTO getTripDTOById(int id);

    boolean isTrainAvailableForNewTrip(String trainName, String departureTimeStr, String arrivalTimeStr);

    List<TripDTO> getAllTrips();

    void cancelTrip(int tripId);

    void delayTrip(int tripId, int delay);


}
