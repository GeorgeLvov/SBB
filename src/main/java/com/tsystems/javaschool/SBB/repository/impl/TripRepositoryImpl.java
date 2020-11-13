package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.Trip;
import com.tsystems.javaschool.SBB.repository.interfaces.TripRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TripRepositoryImpl implements TripRepository {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public List<Trip> getAllTrips(){
        Query query = entityManager.createQuery("select t from Trip t order by t.train.id, t.departureTime");
        return query.getResultList();
    }

    @Override
    public void updateDepartureAndArrivalTimes(int tripId, String delayStr){
        Query query = entityManager
                .createNativeQuery("update trip set \n" +
                        "departure_time = case when departure_time > now() " +
                        "then (select ADDTIME(departure_time, ?)) else departure_time end,\n" +
                        "arrival_time = (select ADDTIME(arrival_time, ?))\n" +
                        "where id = ?;");

        query.setParameter(1, delayStr);
        query.setParameter(2, delayStr);
        query.setParameter(3, tripId);

        query.executeUpdate();

    }

    @Override
    public void cancelTrip(int tripId) {
        Query query = entityManager
                .createQuery("update Trip t set t.canceled = 1 where t.id = :tripId");
        query.setParameter("tripId", tripId);
        query.executeUpdate();
    }

    @Override
    public List<Trip> getTripsByTrainId(int trainId) {
        Query query = entityManager.createQuery("select t from Trip t where t.train.id = :trainId");
        query.setParameter("trainId", trainId);
        return query.getResultList();
    }


    @Override
    public Trip getTripById(int id) {
        return entityManager.find(Trip.class, id);
    }

    @Override
    public void add(Trip trip) {
        entityManager.persist(trip);
    }
}
