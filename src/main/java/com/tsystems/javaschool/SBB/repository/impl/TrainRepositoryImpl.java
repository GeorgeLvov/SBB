package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.TrainEntity;
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
    public List<TrainEntity> getAllTrains() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from TrainEntity").list();
    }


    @Override
    public TrainEntity getTrainById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TrainEntity.class, id);
    }

    @Override
    public void add(TrainEntity train) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(train);
    }

    @Override
    public void update(TrainEntity train) {
        Session session = sessionFactory.getCurrentSession();
        session.update(train);
    }

    @Override
    public void delete(TrainEntity train) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(train);
    }
}
