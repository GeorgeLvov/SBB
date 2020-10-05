package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.Ticket;
import com.tsystems.javaschool.SBB.repository.interfaces.TrainRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class TicketRepositoryImpl {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    TrainRepository trainRepository;
    @Transactional
    public void setValidityOfTickets(){
        Session session = sessionFactory.getCurrentSession();
        session.createNativeQuery("update ticket set valid = 0 where arrival_time < now()").executeUpdate();
    }

    @Transactional
    public List<Ticket> getAllTickets(){
        setValidityOfTickets();
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Ticket").list();
    }

    @Transactional
    public boolean isTrainFull(Timestamp departureTime, int trainId, int tripId) {
        setValidityOfTickets();
        Session session = sessionFactory.getCurrentSession();
        Query query = session
                .createNativeQuery("select count(*) from ticket where train_id=? and trip_id=? and valid=1 and arrival_time > ?");
        query.setParameter(1, trainId);
        query.setParameter(2, tripId);
        query.setParameter(3, departureTime);
        BigInteger bigInteger = (BigInteger) query.getSingleResult();
        return trainRepository.getTrainById(trainId).getCapacity() <= bigInteger.longValue();

    }
}
