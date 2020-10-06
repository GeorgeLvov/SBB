package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.TicketDTO;

public interface TicketService {

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
}
