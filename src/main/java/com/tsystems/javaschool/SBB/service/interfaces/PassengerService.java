package com.tsystems.javaschool.SBB.service.interfaces;


import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.dto.PassengerInfo;
import com.tsystems.javaschool.SBB.dto.TicketDTO;

import java.sql.Date;
import java.util.List;


public interface PassengerService {

    /**
     * Checks if a passenger with the specified personal data has checked in for a specific trip
     *
     * @param firstName passenger firstname
     * @param lastName passenger lastname
     * @param ticketDTO contains info about trip
     * @return false if such passenger already checked in for the trip
     */
    boolean isPassengerAlreadyCheckedIn(String firstName, String lastName, Date birthDate, TicketDTO ticketDTO);


    /**
     * Method for searching all passengers registered for the entire train trip
     *
     * @param trainId id of the specific train
     * @param tripId  id of the specific trip
     * @return list of PassengerInfo(Passenger personal data and their trip details)
     * */
    List<PassengerInfo> getAllPassengersByTrainIdAndTripId(int trainId, int tripId);

    PassengerDTO findPassengerByPersonalData(String firstName, String lastName, Date birthDate);

    void addPassenger(PassengerDTO passengerDTO);


}
