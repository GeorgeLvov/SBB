package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.PassengerEntity;
import com.tsystems.javaschool.SBB.repository.PassengerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PassengerRepositoryImpl implements PassengerRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<PassengerEntity> getAllPassengers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from PassengerEntity").list();
    }


    @Override
    public PassengerEntity getPassengerById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(PassengerEntity.class, id);
    }

    @Override
    public void add(PassengerEntity passenger) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(passenger);
    }

    @Override
    public void update(PassengerEntity passenger) {
        Session session = sessionFactory.getCurrentSession();
        session.update(passenger);
    }

    @Override
    public void delete(PassengerEntity passenger) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(passenger);
    }
}
