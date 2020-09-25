package com.tsystems.javaschool.SBB.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScheduleRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Object[]> loadSchedule(String station){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createNativeQuery("select trains.`name`, stations.`name`, departure_time from `schedule`      \n" +
                "inner join stations on `schedule`.station_from = stations.id\n" +
                "inner join trains on trains.id = `schedule`.train_id\n" +
                "where stations.`name` = ?");
        query.setParameter(1, "SPB");
        return null;
    }
}
