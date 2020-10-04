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

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Schedule> getAllSchedules() {
        return null;
    }

    @Override
    public Schedule getScheduleById(int id) {
        return null;
    }

    @Override
    public void add(Schedule schedule) {

    }

    @Override
    public List<Schedule> getSchedulesByDepartureStationAndTime(Station stationFrom, Timestamp tmp1, Timestamp tmp2) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session
                .createQuery("from Schedule s where (s.departureTime between :tmp1 and :tmp2) and s.stationFrom = :stationFrom order by s.departureTime")
                .setParameter("stationFrom", stationFrom)
                .setParameter("tmp1", tmp1)
                .setParameter("tmp2", tmp2);
        return query.list();
    }

    @Override
    public List<Schedule> getSchedulesByStationTo(Station stationTo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session
                .createQuery("from Schedule s where s.stationTo = :stationTo")
                .setParameter("stationTo", stationTo);
        List<Schedule> list = query.list();
        System.out.println("REPOSITORY!!!!!!!!!!!!!!!!!!!!!!!!!!!---------------------------");
        list.forEach(System.out::println);
        return query.list();
    }

    @Override
    public List<Schedule> getSchedulesByTrainIdAndTripId(int trainId, int tripId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session
                .createQuery("from Schedule s where s.train.id = :trainId and s.trip.id = :tripId")
                .setParameter("trainId", trainId)
                .setParameter("tripId", tripId);
        return query.list();
    }


}
