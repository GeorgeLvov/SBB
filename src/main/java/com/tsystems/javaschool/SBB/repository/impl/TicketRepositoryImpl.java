package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.Ticket;
import com.tsystems.javaschool.SBB.repository.interfaces.TicketRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TrainRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;


/**
 * Implementation of {@link TicketRepository} interface.
 *
 * @author George Lvov
 * @version 1.0
 */

@Repository
public class TicketRepositoryImpl implements TicketRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private TrainRepository trainRepository;

    @Override
    public Ticket getTicketById(int id) {
        return entityManager.find(Ticket.class, id);
    }


    @Override
    public void setValidityOfTickets() {
        entityManager.createNativeQuery("update ticket set valid = 0 where arrival_time < now()").executeUpdate();
    }

    @Override
    public BigInteger getTakenSeatsCount(int trainId, int tripId, Timestamp departureTime, Timestamp arrivalTime) {
        Query query = entityManager
                .createNativeQuery("select count(*) from ticket where train_id=? and trip_id=? " +
                        "and valid=1 and arrival_time >= ? and departure_time <= ?");
        query.setParameter(1, trainId);
        query.setParameter(2, tripId);
        query.setParameter(3, departureTime);
        query.setParameter(4, arrivalTime);

        return (BigInteger) query.getSingleResult();
    }


    @Override
    public List<Object[]> getAllTicketsByUserId(int userId) {
        Query query = entityManager
                .createNativeQuery("select ticket.id, tr.name as tname, p.firstname, p.lastname, p.birthdate, " +
                        "st1.name as stfname, st2.name as sttname, ticket.departure_time, ticket.arrival_time, " +
                        "ticket.valid FROM ticket " +
                        "inner join train tr on tr.id = ticket.train_id " +
                        "inner join passenger p on p.id = ticket.passenger_id " +
                        "inner join station st1 on st1.id = ticket.station_from_id " +
                        "inner join station st2 on st2.id = ticket.station_to_id " +
                        "where ticket.user_id=?;");
        query.setParameter(1, userId);
        return query.getResultList();
    }


    @Override
    public void add(Ticket ticket) {
        entityManager.persist(ticket);
    }
}
