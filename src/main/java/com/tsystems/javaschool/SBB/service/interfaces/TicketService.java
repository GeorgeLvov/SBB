package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.PassengerDTO;
import com.tsystems.javaschool.SBB.dto.TicketDTO;
import com.tsystems.javaschool.SBB.dto.TicketInfo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface TicketService {


    TicketDTO getTicketDTOById(int id);

    void createTicket(TicketDTO ticketDTO);

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

    void setPassengerToTicket(PassengerDTO passengerDTO);

    void prepareTicketForPassenger(Map<String, String> allRequestParams);
}
