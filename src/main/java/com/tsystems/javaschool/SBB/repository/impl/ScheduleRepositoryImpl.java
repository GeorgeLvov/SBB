package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.Schedule;
import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.entities.Train;
import com.tsystems.javaschool.SBB.repository.interfaces.ScheduleRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Implementation of {@link ScheduleRepository} interface.
 *
 * @author George Lvov
 * @version 1.0
 */

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void add(Schedule schedule) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(schedule);
    }

    @Override
    public List<Schedule> getSchedulesByDepartureStationAndTime(Station stationFrom, Timestamp dateFrom, Timestamp dateTo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session
                .createQuery("from Schedule s where (s.departureTime between :tmp1 and :tmp2) and s.stationFrom = :stationFrom order by s.departureTime")
                .setParameter("stationFrom", stationFrom)
                .setParameter("tmp1", dateFrom)
                .setParameter("tmp2", dateTo);
        return query.list();
    }

    @Override
    public List<Schedule> getSchedulesByStationFrom(Station stationFrom) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session
                .createQuery("from Schedule s where s.stationFrom = :stationFrom order by s.departureTime")
                .setParameter("stationFrom", stationFrom);
        return query.list();
    }

    @Override
    public List<Schedule> getSchedulesByTrainIdTripIdStationTo(int trainId, int tripId, Station stationTo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session
                .createQuery("from Schedule s where s.train.id =:trainId and s.tripId =:tripId and s.stationTo = :stationTo")
                .setParameter("trainId", trainId)
                .setParameter("tripId", tripId)
                .setParameter("stationTo", stationTo);
        return query.list();
    }


    @Override
    public List<Schedule> getSchedulesByTrainIdAndTripId(int trainId, int tripId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session
                .createQuery("from Schedule s where s.train.id = :trainId and s.tripId = :tripId")
                .setParameter("trainId", trainId)
                .setParameter("tripId", tripId);
        return query.list();
    }

    @Override
    public Integer getMaxTripId() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select max(s.tripId) from Schedule s");
        return (Integer) query.getSingleResult();
    }


    @Override
    public List<Timestamp> getAllDepartureTimesByTrainId(int trainId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createNativeQuery("select s1.departure_time from schedule s1\n" +
                "inner join\n" +
                "(select trip_id, min(station_index) as MinStIndex from schedule\n" +
                "where train_id = ? group by trip_id) groupedSched\n" +
                "on s1.trip_id = groupedSched.trip_id\n" +
                "and s1.station_index = groupedSched.MinStIndex;");
        query.setParameter(1, trainId);

        return (List<Timestamp>) query.list();
    }


    @Override
    public List<Timestamp> getAllArrivalTimesByTrainId(int trainId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createNativeQuery("select s1.arrival_time from schedule s1\n" +
                "inner join\n" +
                "(select trip_id, max(station_index) as MaxStIndex from schedule\n" +
                "where train_id = ? group by trip_id) groupedSched\n" +
                "on s1.trip_id = groupedSched.trip_id\n" +
                "and s1.station_index = groupedSched.MaxStIndex;");
        query.setParameter(1, trainId);

        return (List<Timestamp>) query.list();
    }

    @Override
    public List<Object[]> getAllTrainsTrips(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createNativeQuery("select train_id, trip_id from schedule\n" +
                "group by train_id,trip_id order by departure_time");
        return query.list();
    }



}
