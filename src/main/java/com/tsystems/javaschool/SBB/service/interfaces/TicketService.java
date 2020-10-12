package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTOContainer;
import com.tsystems.javaschool.SBB.dto.TicketInfoDTO;

import java.sql.Timestamp;
import java.util.List;

public interface TicketService {


    TicketDTO getTicketDTOById(int id);

    void add(TicketDTOContainer ticketDTOContainer);

    void setValidityOfTickets();

    boolean isTrainFull(Timestamp departureTime, Timestamp arrivalTime, int trainId, int tripId);


    /**
     * Checks by time whether it is still possible to buy a ticket
     *
     * @param departureTime departure time of a specific trip
     * @return false if there are at least 10 minutes left before the train departure
     */
    boolean isTimeValid(Timestamp departureTime);

    List<TicketInfoDTO> getAllTicketInfosByUsername(String username);
}
