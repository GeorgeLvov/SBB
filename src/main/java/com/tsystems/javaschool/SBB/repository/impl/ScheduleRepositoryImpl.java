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
    public void updateTimes(int tripId, int delay, String delayStr){

        Query query = entityManager.createNativeQuery("update schedule set \n" +
                "departure_time = case when departure_time > now() then (select ADDTIME(departure_time, ?)) " +
                "else departure_time end,\n" +
                "arrival_time = case when arrival_time > now() then (select ADDTIME(arrival_time, ?)) " +
                "else arrival_time end,\n" +
                "departure_delay = case when departure_time > now() then departure_delay + ? " +
                "else departure_delay end,\n" +
                "arrival_delay = case when arrival_time > now() then arrival_delay + ? else arrival_delay end\n" +
                "where trip_id = ?;");

        query.setParameter(1, delayStr);
        query.setParameter(2, delayStr);
        query.setParameter(3, delay);
        query.setParameter(4, delay);
        query.setParameter(5, tripId);

        query.executeUpdate();
    }



    @Override
    public void add(Schedule schedule) {
        entityManager.persist(schedule);
    }

    @Override
    public List<Schedule> getSchedulesByDepartureStationAndTime(Station stationFrom, Timestamp dateFrom,
                                                                Timestamp dateTo) {
        Query query = entityManager
                .createQuery("select s from Schedule s where (s.departureTime between :tmp1 and :tmp2)" +
                        " and s.stationFrom = :stationFrom and s.trip.canceled = false order by s.departureTime")
                .setParameter("stationFrom", stationFrom)
                .setParameter("tmp1", dateFrom)
                .setParameter("tmp2", dateTo);
        return query.getResultList();
    }


    @Override
    public List<Schedule> getSchedulesByTripIdAndStationTo(int tripId, Station stationTo) {
        Query query = entityManager
                .createQuery("select s from Schedule s where s.trip.id =:tripId and s.stationTo = :stationTo")
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


    @SuppressWarnings("unchecked")
    @Override
    public List<TimetableDTO> getDepartureTimetableByStationId(Integer stationId) {

        Query query = entityManager.createQuery("select " +
                "new com.tsystems.javaschool.SBB.dto.TimetableDTO(s.trip.train.trainName, s.stationFrom.title, " +
                "s.trip.arrivalStation.title, s.departureTime, s.trip.arrivalTime," +
                "s.departureDelay, s.trip.canceled) " +
                "from Schedule s where s.stationFrom.id =: stationId " +
                "and date(s.departureTime) = current_date order by s.departureTime");

/*        Query query =
                entityManager.createQuery("select " +
                        "new com.tsystems.javaschool.SBB.dto.TimetableDTO(s.trip.train.trainName, " +
                        "s.stationFrom.title, s.trip.arrivalStation.title, s.departureTime, s.trip.arrivalTime," +
                        " s.departureDelay, s.trip.canceled) " +
                        "from Schedule s where s.stationFrom.id =: stationId " +
                        "order by s.departureTime");*/

        query.setParameter("stationId", stationId);

        return query.getResultList();
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<TimetableDTO> getArrivalTimetableByStationId(Integer stationId) {

        Query query = entityManager.createQuery("select " +
                "new com.tsystems.javaschool.SBB.dto.TimetableDTO(s.trip.train.trainName, s.trip.departureStation.title, " +
                "s.stationTo.title, s.trip.departureTime, s.arrivalTime, s.arrivalDelay, s.trip.canceled) " +
                "from Schedule s where s.stationTo.id =: stationId and date(s.arrivalTime) = current_date " +
                "order by s.arrivalTime");

/*       Query query =
                entityManager.createQuery("select new com.tsystems.javaschool.SBB.dto.TimetableDTO(s.trip.train.trainName, " +
                        "s.trip.departureStation.title, s.stationTo.title, s.trip.departureTime, s.arrivalTime," +
                        " s.arrivalDelay, s.trip.canceled) from Schedule s where s.stationTo.id =: stationId " +
                        "order by s.arrivalTime");*/

        query.setParameter("stationId", stationId);

        return query.getResultList();
    }


}
