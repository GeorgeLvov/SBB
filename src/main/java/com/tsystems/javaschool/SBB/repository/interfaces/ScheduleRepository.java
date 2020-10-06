package com.tsystems.javaschool.SBB.repository.interfaces;

import com.tsystems.javaschool.SBB.entities.Schedule;
import com.tsystems.javaschool.SBB.entities.Station;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleRepository {

    void add(Schedule schedule);

    /**
     * Provides all schedules with specified params
     * Helper method for searching trips from station A to station B at a given time
     *
     * @param stationFrom starting station
     * @param dateFrom    the beginning of the time period
     * @param dateTo      the end of the time period
     */
    List<Schedule> getSchedulesByDepartureStationAndTime(Station stationFrom, Timestamp dateFrom, Timestamp dateTo);


    /**
     * Provides all schedules with specified params
     * Helper method like {@link #getSchedulesByDepartureStationAndTime(Station stationFrom, Timestamp dateFrom, Timestamp dateTo) getSchedulesByDepartureStationAndTime}
     * for searching trips from station A to station B at a given time
     *
     * @param stationTo end station
     */
    List<Schedule> getSchedulesByStationTo(Station stationTo);


    /**
     * Returns all schedules with specified params
     *
     * @param trainId id of train
     * @param tripId  id of trip
     */
    List<Schedule> getSchedulesByTrainIdAndTripId(int trainId, int tripId);
}
