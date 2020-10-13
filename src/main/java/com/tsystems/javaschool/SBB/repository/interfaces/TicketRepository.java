package com.tsystems.javaschool.SBB.repository.interfaces;

import com.tsystems.javaschool.SBB.entities.Ticket;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

public interface TicketRepository {

    Ticket getTicketById(int id);

    void add(Ticket ticket);

    /**
     * Invalidates the ticket if the end time of the trip has passed
     */
    void setValidityOfTickets();

    /**
     * Provides the number of taken seats on the train for a specific trip
     *
     * @param trainId       id of train
     * @param tripId        id of trip
     * @param departureTime train departure time
     * @return number of taken seats in {@link BigInteger} format
     */
    BigInteger getTakenSeatsCount(int trainId, int tripId, Timestamp departureTime, Timestamp arrivalTime);


    List<Object[]> getAllTicketsByUserId(int userId);
}
