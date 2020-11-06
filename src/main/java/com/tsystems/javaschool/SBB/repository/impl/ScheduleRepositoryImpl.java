package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.dto.TimetableDTO;
import com.tsystems.javaschool.SBB.entities.Schedule;
import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.repository.interfaces.ScheduleRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


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


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void add(Schedule schedule) {
        entityManager.persist(schedule);
    }

    @Transactional
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

    @Transactional
    @Override
    public List<Schedule> getSchedulesByTrainIdTripIdStationTo(int trainId, int tripId, Station stationTo) {
        Query query = entityManager
                .createQuery("select s from Schedule s where s.train.id =:trainId and s.trip.id =:tripId" +
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
    public List<Schedule> getSchedulesByTripId(int tripId) {
        Query query = entityManager
                .createQuery("select s from Schedule s where s.trip.id = :tripId")
                .setParameter("tripId", tripId);
        return query.getResultList();
    }

    @Override
    public void updateTimes(int tripId, String delayStr){
        Query query = entityManager
                .createNativeQuery("update schedule set departure_time=(select ADDTIME(departure_time, ?)),\n" +
                        "arrival_time=(select ADDTIME(arrival_time, ?)) where trip_id = ?");
        query.setParameter(1, delayStr);
        query.setParameter(2, delayStr);
        query.setParameter(3, tripId);
        query.executeUpdate();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TimetableDTO> getDepartureTimetableByStationId(Integer stationId) {

/*        Query query =
                    entityManager.createQuery("select new com.tsystems.javaschool.SBB.dto.TimetableDTO(s.train.trainName, " +
                            "s.stationFrom.title, s.trip.arrivalStation.title, s.departureTime, s.trip.arrivalTime," +
                            " s.trip.delay, s.trip.canceled) from Schedule s where s.stationFrom.id =: stationId and " +
                            "date(s.departureTime) = current_date order by s.departureTime");*/
        Query query =
                entityManager.createQuery("select new com.tsystems.javaschool.SBB.dto.TimetableDTO(s.train.trainName, " +
                        "s.stationFrom.title, s.trip.arrivalStation.title, s.departureTime, s.trip.arrivalTime," +
                        " s.trip.delay, s.trip.canceled) from Schedule s where s.stationFrom.id =: stationId " +
                        "order by s.departureTime");

        query.setParameter("stationId", stationId);

        return query.getResultList();
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<TimetableDTO> getArrivalTimetableByStationId(Integer stationId) {

       /* Query query =
                entityManager.createQuery("select new com.tsystems.javaschool.SBB.dto.TimetableDTO(s.train.trainName, " +
                        "s.trip.departureStation.title, s.stationTo.title, s.trip.departureTime, s.arrivalTime," +
                        " s.trip.delay, s.trip.canceled) from Schedule s where s.stationTo.id =: stationId and " +
                        "date(s.arrivalTime) = current_date order by s.arrivalTime");*/
        Query query =
                entityManager.createQuery("select new com.tsystems.javaschool.SBB.dto.TimetableDTO(s.train.trainName, " +
                        "s.trip.departureStation.title, s.stationTo.title, s.trip.departureTime, s.arrivalTime," +
                        " s.trip.delay, s.trip.canceled) from Schedule s where s.stationTo.id =: stationId " +
                        "order by s.arrivalTime");

        query.setParameter("stationId", stationId);

        return query.getResultList();
    }


}
