package com.tsystems.javaschool.SBB.service.interfaces;


import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.dto.PassengerInfoDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTOContainer;

import java.sql.Date;
import java.util.List;


public interface PassengerService {

    PassengerDTO getPassengerDTOById(int id);

    PassengerDTO findPassengerByPersonalData(String firstName, String lastName, Date birthDate);

    boolean isPassengerAlreadyCheckedIn(String firstName, String lastName, Date birthDate, TicketDTOContainer ticketDTOContainer);

    List<PassengerInfoDTO> getAllPassengersByTrainIdAndTripId(int trainId, int tripId);

    void add(PassengerDTO passengerDTO);

    void update(PassengerDTO passengerDTO);

    void delete(PassengerDTO passengerDTO);

}
