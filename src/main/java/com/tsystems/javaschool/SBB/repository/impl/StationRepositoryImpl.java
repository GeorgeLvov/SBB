package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.StationEntity;
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
    public List<StationEntity> getAllStations() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from StationEntity").list();
    }


    @Override
    public StationEntity getStationById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(StationEntity.class, id);
    }

    @Override
    public void add(StationEntity station) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(station);
    }

    @Override
    public void update(StationEntity station) {
        Session session = sessionFactory.getCurrentSession();
        session.update(station);
    }

    @Override
    public void delete(StationEntity station) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(station);
    }
}
