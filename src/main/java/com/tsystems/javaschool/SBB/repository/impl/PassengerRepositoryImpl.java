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
        /*for (Object[] objects : list) {
            int trainId = (int) objects[0];
            int tripId = (int) objects[1];
            Timestamp departureTime = (Timestamp) objects[2];
            Timestamp arrivalTime = (Timestamp) objects[3];
            System.out.println("trainId" + trainId);
            System.out.println("tripId" + tripId);
            System.out.println("departureTime" + departureTime);
            System.out.println("arrivalTime" + arrivalTime);
        }*/
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


