package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.ScheduleDTO;
import com.tsystems.javaschool.SBB.dto.StationDTO;

import java.util.List;

public interface ScheduleService {

    /**
     * Provides all schedules by specified departure station
     *
     * @param stationDTOFrom data transfer object represents entity {@link com.tsystems.javaschool.SBB.entities.Station}
     * @return List data transfer objects represents {@link com.tsystems.javaschool.SBB.entities.Schedule}
     */
    List<ScheduleDTO> getSchedulesByStationFrom(StationDTO stationDTOFrom);


    /**
     * Provides all schedules by arrival station
     *
     * @param stationDTOTo data transfer object represents entity {@link com.tsystems.javaschool.SBB.entities.Station}
     * @return List data transfer objects represents {@link com.tsystems.javaschool.SBB.entities.Schedule}
     */
    List<ScheduleDTO> getSchedulesByStationTo(StationDTO stationDTOTo);


    /**
     * Provides all existing schedules between specified stations at a specified time interval
     *
     * @param stationFromId id of the departure station
     * @param stationToId id of the departure station
     * @param dateTimeFrom schedule search start time in string representation
     * @param dateTimeTo schedule search end time in string representation
     * @return List data transfer objects represents {@link com.tsystems.javaschool.SBB.entities.Schedule}
     */
    List<ScheduleDTO> getSchedulesByStationsAndDate(Integer stationFromId, Integer stationToId, String dateTimeFrom,
                                                    String dateTimeTo);

    /**
     * Provides all schedules by specified trip
     *
     * @param tripId id of the trip
     * @return List data transfer objects represents {@link com.tsystems.javaschool.SBB.entities.Schedule}
     */
    List<ScheduleDTO> getSchedulesByTripId(int tripId);


    /**
     *  Creates trip and all its schedules.
     *
     *  Sends message into topic for timetable application after creating a trip
     * */
    void createSchedulesAndTrip();

}
