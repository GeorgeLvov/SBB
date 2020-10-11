package com.tsystems.javaschool.SBB.repository.interfaces;

import com.tsystems.javaschool.SBB.entities.Schedule;
import com.tsystems.javaschool.SBB.entities.Station;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleRepository {

    void add(Schedule schedule);

    /**
     * Provides all schedules with specified params
     * Helper method like {@link #getSchedulesByStationTo(Station To) getSchedulesByStationTo}
     * for searching trips from station A to station B at a given time
     *
     * @param stationFrom starting station
     * @param dateFrom    the beginning of the time period
     * @param dateTo      the end of the time period
     */
    List<Schedule> getSchedulesByDepartureStationAndTime(Station stationFrom, Timestamp dateFrom, Timestamp dateTo);

    /**
     * Provides all schedules with specified param
     *
     * @param stationFrom chosen station for timetable
     */
    List<Schedule> getSchedulesByStationFrom(Station stationFrom);

    /**
     * Provides all schedules with specified param
     * Helper method for searching trips from station A to station B at a given time
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

    Integer getMaxTripId();

    List<Timestamp> getAllDepartureTimesByTrainId(int trainId);

    List<Timestamp> getAllArrivalTimesByTrainId(int trainId);
}
