package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.Schedule;
import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.repository.interfaces.ScheduleRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;


/**
 * Implementation of {@link ScheduleRepository} interface.
 *
 * @author George Lvov
 * @version 1.0
 */

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void add(Schedule schedule) {
        entityManager.persist(schedule);
    }

    @Override
    public List<Schedule> getSchedulesByDepartureStationAndTime(Station stationFrom, Timestamp dateFrom, Timestamp dateTo) {
        Query query = entityManager
                .createQuery("select s from Schedule s where (s.departureTime between :tmp1 and :tmp2)" +
                        " and s.stationFrom = :stationFrom order by s.departureTime")
                .setParameter("stationFrom", stationFrom)
                .setParameter("tmp1", dateFrom)
                .setParameter("tmp2", dateTo);
        return query.getResultList();
    }

    @Override
    public List<Schedule> getSchedulesByTrainIdTripIdStationTo(int trainId, int tripId, Station stationTo) {
        Query query = entityManager
                .createQuery("select s from Schedule s where s.train.id =:trainId and s.tripId =:tripId" +
                        " and s.stationTo = :stationTo")
                .setParameter("trainId", trainId)
                .setParameter("tripId", tripId)
                .setParameter("stationTo", stationTo);
        return query.getResultList();
    }


    @Override
    public List<Schedule> getSchedulesByStationFrom(Station stationFrom) {
        Query query = entityManager
                .createQuery("select s from Schedule s where s.stationFrom = :stationFrom order by s.departureTime")
                .setParameter("stationFrom", stationFrom);
        return query.getResultList();
    }

    @Override
    public List<Schedule> getSchedulesByStationTo(Station stationTo) {
        Query query = entityManager
                .createQuery("select s from Schedule s where s.stationTo = :stationTo order by s.arrivalTime")
                .setParameter("stationTo", stationTo);
        return query.getResultList();
    }


    @Override
    public List<Schedule> getSchedulesByTrainIdAndTripId(int trainId, int tripId) {
        Query query = entityManager
                .createQuery("select s from Schedule s where s.train.id = :trainId and s.tripId = :tripId")
                .setParameter("trainId", trainId)
                .setParameter("tripId", tripId);
        return query.getResultList();
    }

    @Override
    public Integer getMaxTripId() {
        Query query = entityManager.createQuery("select max(s.tripId) from Schedule s");
        return (Integer) query.getSingleResult();
    }


    @Override
    public List<Timestamp> getAllDepartureTimesByTrainId(int trainId) {
        Query query = entityManager.createNativeQuery("select s1.departure_time from schedule s1\n" +
                "inner join\n" +
                "(select trip_id, min(station_index) as MinStIndex from schedule\n" +
                "where train_id = ? group by trip_id) groupedSched\n" +
                "on s1.trip_id = groupedSched.trip_id\n" +
                "and s1.station_index = groupedSched.MinStIndex;");
        query.setParameter(1, trainId);

        return (List<Timestamp>) query.getResultList();
    }


    @Override
    public List<Timestamp> getAllArrivalTimesByTrainId(int trainId) {
        Query query = entityManager.createNativeQuery("select s1.arrival_time from schedule s1\n" +
                "inner join\n" +
                "(select trip_id, max(station_index) as MaxStIndex from schedule\n" +
                "where train_id = ? group by trip_id) groupedSched\n" +
                "on s1.trip_id = groupedSched.trip_id\n" +
                "and s1.station_index = groupedSched.MaxStIndex;");
        query.setParameter(1, trainId);

        return (List<Timestamp>) query.getResultList();
    }

    @Override
    public List<Object[]> getAllTrainsAndTrips(){
        Query query = entityManager.createNativeQuery("select train_id, trip_id from schedule\n" +
                "group by trip_id order by train_id, departure_time");
        return query.getResultList();
    }



}
