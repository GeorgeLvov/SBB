package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.Passenger;
import com.tsystems.javaschool.SBB.repository.interfaces.PassengerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.NoResultException;
import java.sql.Date;
import java.util.List;

@Repository
public class PassengerRepositoryImpl implements PassengerRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Passenger> getAllPassengers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Passenger").list();
    }

    @Override
    public Passenger getPassengerById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Passenger.class, id);
    }

    @Override
    public List<Object[]> getPassengerWithTicketsByFields(String firstName, String lastName, Date birthDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createNativeQuery("select train_id, trip_id, departure_time, arrival_time from passenger inner join ticket on passenger.id = passenger_id " +
                "where firstname=? and lastname=? and birthdate=? and valid = 1")
                .setParameter(1, firstName)
                .setParameter(2, lastName)
                .setParameter(3, birthDate);
        return query.list();
    }

    @Override
    public Passenger findPassengerByPersonalData(String firstName, String lastName, Date birthDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Passenger p where p.firstName = :firstName and p.lastName = :lastName" +
                " and p.birthDate = :birthDate")
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .setParameter("birthDate", birthDate);
        Passenger passenger = null;
        try {
            passenger = (Passenger) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        return passenger;
    }

    @Override
    public List<Object[]> getAllPassengersByTrainIdAndTripId(int trainId, int tripId){

        Query query = sessionFactory.getCurrentSession().createNativeQuery("select trains.name as trname, firstname, lastname, birthdate, st1.name as st1name, st2.name as st2name, departure_time, arrival_time from passenger\n" +
                "inner join ticket on passenger.id = ticket.passenger_id\n" +
                "inner join trains on train_id=trains.id\n" +
                "inner join stations st1 on station_from_id = st1.id\n" +
                "inner join stations st2 on station_to_id = st2.id\n" +
                "where train_id = ? and trip_id = ?");
        query.setParameter(1, trainId);
        query.setParameter(2, tripId);

        return query.list();
    }

    @Override
    public void add(Passenger passenger) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(passenger);
    }

    @Override
    public void update(Passenger passenger) {
        Session session = sessionFactory.getCurrentSession();
        session.update(passenger);
    }

    @Override
    public void delete(Passenger passenger) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(passenger);
    }

}


