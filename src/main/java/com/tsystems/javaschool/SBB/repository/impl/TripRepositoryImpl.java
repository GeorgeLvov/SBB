package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.Trip;
import com.tsystems.javaschool.SBB.repository.interfaces.TripRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TripRepositoryImpl implements TripRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Trip> getAllTrips() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Trip").list();
    }

    @Override
    public Trip getTripById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Trip.class, id);
    }

    @Override
    public void addTrip(Trip Trip) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(Trip);
    }
}
