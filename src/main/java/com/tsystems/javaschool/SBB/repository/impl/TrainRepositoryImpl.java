package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.Train;
import com.tsystems.javaschool.SBB.repository.interfaces.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TrainRepositoryImpl implements TrainRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Train> getAllTrains() {
        return entityManager.createQuery("select t from Train t").getResultList();
    }


    @Override
    public Train getTrainById(int id) {
        return entityManager.find(Train.class, id);
    }

    @Override
    public Train findTrainByName(String trainName) {
        try {
            Query query = entityManager.createQuery("select t from Train t where t.trainName = :trainName");
            query.setParameter("trainName", trainName);
            return (Train) query.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }

    @Override
    public void add(Train train) {
        entityManager.persist(train);
    }

    @Override
    public void update(Train train) {
     entityManager.merge(train);
    }

    @Override
    public void delete(Train train) {
        entityManager.remove(entityManager.merge(train));
    }
}
