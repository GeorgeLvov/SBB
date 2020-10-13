package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.ScheduleDTO;
import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.TripInfoDTO;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleService {

    void add(ScheduleDTO scheduleDTO);

    List<ScheduleDTO> getSchedulesByStationFrom(StationDTO stationDTOFrom);

    List<ScheduleDTO> getSchedulesByStationTo(StationDTO stationDTOTo);

    List<ScheduleDTO> getSchedulesByStationsAndDate(StationDTO stationFromDTO, StationDTO stationTo, Timestamp tmp1, Timestamp tmp2);

    List<TripInfoDTO> getInfoOfAllTripsByTrainIdAndTripId(int trainId, int tripId);

    void createTrip();

    boolean isTrainAvailableForNewTrip(String trainName, String departureTimeStr, String arrivalTimeStr);

    List<List<TripInfoDTO>> getAllTrips();
}
