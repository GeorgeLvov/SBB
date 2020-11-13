package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.dto.TicketInfo;

import java.sql.Timestamp;
import java.util.List;


public interface TicketService {

    /**
     * Checks if there are free seats of the specific trip in train
     * @param departureTime departure time of a specific trip
     * @param arrivalTime departure time of a specific trip
     * @param trainId id of the train
     * @param tripId id of the trip
     * @return false if there are no more free seats
     */
    boolean isTrainFull(Timestamp departureTime, Timestamp arrivalTime, int trainId, int tripId);

    /**
     * Provides all tickets with info about passengers by specified username
     *
     * @param username specified username
     * */
    List<TicketInfo> getAllUserTickets(String username);

    /**
     * Checks by time whether it is still possible to buy a ticket
     *
     * @param departureTime departure time of a specific trip
     * @return false if there are at least 10 minutes left before the train departure
     */
    boolean isTimeValid(Timestamp departureTime);


    /**
     * Invalidates the ticket if the end time of the trip has passed.
     */
    void setValidityOfTickets();


    /**
     * Exports ticket to PDF format.
     * */
    void exportToPDF(int id);

    /**
     * Method for creating new ticket.
     *
     * @param ticketDTO data transfer object represents entity {@link com.tsystems.javaschool.SBB.entities.Ticket}
     * */
    void createTicket(TicketDTO ticketDTO);


    TicketDTO getTicketDTOById(int id);
}
