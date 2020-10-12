package com.tsystems.javaschool.SBB.service.interfaces;


import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTOContainer;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;


public interface PassengerService {

    PassengerDTO getPassengerDTOById(int id);

    PassengerDTO findPassengerByPersonalData(String firstName, String lastName, Date birthDate);

    boolean isPassengerAlreadyCheckedIn(String firstName, String lastName, Date birthDate, TicketDTOContainer ticketDTOContainer);

    void add(PassengerDTO passengerDTO);

    void update(PassengerDTO passengerDTO);

    void delete(PassengerDTO passengerDTO);

}
