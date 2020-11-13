package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.TripDTO;


import java.util.List;

public interface TripService {

    /**
     * Checks if the train can travel within the specified time period.
     *
     * @return false if the train is already en route at this time.
     * */
    boolean isTrainAvailableForSuchTrip(String trainName, String departureTimeStr, String arrivalTimeStr);


    /**
     * Returns all trips including canceled.
     *
     * @return List with all trips
     * */
    List<TripDTO> getAllTrips();


    /**
     * Delays trip on certain sections of the path.
     * Sends message into topic for timetable application after delaying a trip
     *
     * @param tripId id of specified trip
     * @param delay int value of the delay in minutes
     * */
    void delayTrip(int tripId, int delay);


    /**
     * Cancels the trip with his entire schedule.
     * Sends message into topic for timetable application after canceling a trip
     *
     * @param tripId id of specified trip
     * */
    void cancelTrip(int tripId);



    TripDTO getTripDTOById(int id);

}
