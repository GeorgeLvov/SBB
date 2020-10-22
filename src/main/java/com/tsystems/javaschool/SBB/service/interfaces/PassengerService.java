package com.tsystems.javaschool.SBB.service.interfaces;


import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.dto.PassengerInfoDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTO;

import java.sql.Date;
import java.util.List;


public interface PassengerService {

    PassengerDTO getPassengerDTOById(int id);

    PassengerDTO findPassengerByPersonalData(String firstName, String lastName, Date birthDate);

    boolean isPassengerAlreadyCheckedIn(String firstName, String lastName, Date birthDate, TicketDTO ticketDTO);

    List<PassengerInfoDTO> getAllPassengersByTrainIdAndTripId(int trainId, int tripId);

    void addPassenger(PassengerDTO passengerDTO);


}
