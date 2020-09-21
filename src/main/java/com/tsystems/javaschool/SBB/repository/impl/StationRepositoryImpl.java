package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.repository.StationRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StationRepositoryImpl implements StationRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Station> getAllStations() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Station").list();
    }


    @Override
    public Station getStationById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Station.class, id);
    }

    @Override
    public void add(Station station) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(station);
    }

    @Override
    public void update(Station station) {
        Session session = sessionFactory.getCurrentSession();
        session.update(station);
    }

    @Override
    public void delete(Station station) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(station);
    }
}
