package com.tsystems.javaschool.SBB.repository.impl;

import com.tsystems.javaschool.SBB.entities.Schedule;
import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.repository.interfaces.ScheduleRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    private SessionFactory sessionFactory;

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
    public List<Schedule> getSchedulesByStationTo(Station stationTo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session
                .createQuery("from Schedule s where s.stationTo = :stationTo")
                .setParameter("stationTo", stationTo);
        return query.list();
    }


    @Override
    public List<Schedule> getSchedulesByTrainIdAndTripId(int trainId, int tripId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session
                .createQuery("from Schedule s where s.train.id = :trainId and s.trip.id = :tripId")
                .setParameter("trainId", trainId)
                .setParameter("tripId", tripId);
        return query.list();
    }

    @Override
    public void add(Schedule schedule) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(schedule);
    }

}
