package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.dto.TicketInfo;

import java.sql.Timestamp;
import java.util.List;


public interface TicketService {

    void exportToPDF(int id);

    void createTicket(TicketDTO ticketDTO);

    TicketDTO getTicketDTOById(int id);

    void setValidityOfTickets();

    boolean isTrainFull(Timestamp departureTime, Timestamp arrivalTime, int trainId, int tripId);

    /**
     * Checks by time whether it is still possible to buy a ticket
     *
     * @param departureTime departure time of a specific trip
     * @return false if there are at least 10 minutes left before the train departure
     */
    boolean isTimeValid(Timestamp departureTime);

    List<TicketInfo> getAllUserTickets(String username);

}
