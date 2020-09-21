package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.Train;
import com.tsystems.javaschool.SBB.repository.TrainRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainRepositoryImpl implements TrainRepository {
    
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Train> getAllTrains() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Train").list();
    }


    @Override
    public Train getTrainById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Train.class, id);
    }

    @Override
    public void add(Train train) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(train);
    }

    @Override
    public void update(Train train) {
        Session session = sessionFactory.getCurrentSession();
        session.update(train);
    }

    @Override
    public void delete(Train train) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(train);
    }
}
