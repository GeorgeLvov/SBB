package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.Passenger;
import com.tsystems.javaschool.SBB.repository.interfaces.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

@Repository
public class PassengerRepositoryImpl implements PassengerRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Passenger getPassengerById(int id) {
        return entityManager.find(Passenger.class, id);
    }

    @Override
    public List<Object[]> getPassengerWithTicketsByPersonalData(String firstName, String lastName, Date birthDate) {

        Query query = entityManager.createNativeQuery("select train_id, trip_id, departure_time, arrival_time from " +
                "passenger inner join ticket on passenger.id = passenger_id " +
                "where firstname=? and lastname=? and birthdate=? and valid = 1")
                .setParameter(1, firstName)
                .setParameter(2, lastName)
                .setParameter(3, birthDate);
        return query.getResultList();
    }

    @Override
    public Passenger getPassengerByPersonalData(String firstName, String lastName, Date birthDate) {
        Query query = entityManager.createQuery("select p from Passenger p where p.firstName = :firstName and p.lastName = :lastName" +
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

        Query query = entityManager.createNativeQuery("select train.name as trname, firstname, lastname, birthdate, st1.name as st1name, st2.name as st2name, departure_time, arrival_time from passenger\n" +
                "inner join ticket on passenger.id = ticket.passenger_id\n" +
                "inner join train on train_id=train.id\n" +
                "inner join station st1 on station_from_id = st1.id\n" +
                "inner join station st2 on station_to_id = st2.id\n" +
                "where train_id = ? and trip_id = ?");
        query.setParameter(1, trainId);
        query.setParameter(2, tripId);

        return query.getResultList();
    }

    @Override
    public void add(Passenger passenger) {
        entityManager.persist(passenger);
    }


}


