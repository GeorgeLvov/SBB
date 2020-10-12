package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.Ticket;
import com.tsystems.javaschool.SBB.repository.interfaces.TicketRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TrainRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Date;
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

    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    TrainRepository trainRepository;

    @Override
    public Ticket getTicketById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Ticket.class, id);
    }


    @Override
    public void setValidityOfTickets() {
        Session session = sessionFactory.getCurrentSession();
        session.createNativeQuery("update ticket set valid = 0 where arrival_time < now()").executeUpdate();
    }

    @Override
    public BigInteger getTakenSeatsCount(int trainId, int tripId, Timestamp departureTime, Timestamp arrivalTime) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session
                .createNativeQuery("select count(*) from ticket where train_id=? and trip_id=? and valid=1 and arrival_time >= ? and departure_time <= ?");
        query.setParameter(1, trainId);
        query.setParameter(2, tripId);
        query.setParameter(3, departureTime);
        query.setParameter(4, arrivalTime);

        return (BigInteger) query.getSingleResult();
    }


    @Override
    public List<Object[]> getAllTicketsByUserId(int userId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session
                .createNativeQuery("select t.id, tr.name as tname, p.firstname, p.lastname, p.birthdate, st1.name as stfname, st2.name as sttname, t.departure_time, t.arrival_time, t.valid FROM ticket t " +
                        "inner join trains tr on tr.id = t.train_id " +
                        "inner join passenger p on p.id = t.passenger_id " +
                        "inner join stations st1 on st1.id=t.station_from_id " +
                        "inner join stations st2 on st2.id=t.station_to_id " +
                        "where t.user_id=?;");
        query.setParameter(1, userId);
        return query.list();
    }


    @Override
    public void add(Ticket ticket) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(ticket);
    }
}
