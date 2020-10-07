package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.dto.TicketInfoDTO;

import java.util.List;

public interface TicketService {


    TicketDTO getTicketDTOById(int id);

    void add(TicketDTO ticketDTO);

    void setValidityOfTickets();

    boolean isTrainFull(String departureTimeStr, String arrivalTimeStr, int trainId, int tripId);


    /**
     * Checks by time whether it is still possible to buy a ticket
     *
     * @param departureTimeStr departure time of a specific trip
     * @return false if there are at least 10 minutes left before the train departure
     */
    boolean isTimeValid(String departureTimeStr);

    List<TicketInfoDTO> getAllTicketInfosByUsername(String username);
}
