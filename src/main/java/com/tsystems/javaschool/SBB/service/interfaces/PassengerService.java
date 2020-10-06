package com.tsystems.javaschool.SBB.service.interfaces;


import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTO;

import java.sql.Date;


public interface PassengerService {

    PassengerDTO getPassengerDTOById(int id);

    PassengerDTO findPassengerByPersonalData(String firstName, String lastName, Date birthDate);

    boolean isPassengerAlreadyCheckedIn(String firstName, String lastName, Date birthDate, TicketDTO rawTicketDTO);

    void add(PassengerDTO passengerDTO);

    void update(PassengerDTO passengerDTO);

    void delete(PassengerDTO passengerDTO);

}
