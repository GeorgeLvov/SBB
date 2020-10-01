package com.tsystems.javaschool.SBB.repository.interfaces;

import com.tsystems.javaschool.SBB.entities.Schedule;
import com.tsystems.javaschool.SBB.entities.Station;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleRepository {

    List<Schedule> getAllSchedules();

    Schedule getScheduleById(int id);

    void add(Schedule schedule);

    List<Schedule> getSchedulesByDepartureStationAndTime(Station stationFrom, Timestamp tmp1, Timestamp tmp2);

    List<Schedule> getSchedulesByStationTo(Station stationTo);

    List<Schedule> getSchedulesByTrainIdAndTripId(int trainId, int tripId);
}
