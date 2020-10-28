package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.ScheduleDTO;
import com.tsystems.javaschool.SBB.dto.TripInfo;
import com.tsystems.javaschool.SBB.dto.StationDTO;

import java.util.List;

public interface ScheduleService {

    List<ScheduleDTO> getSchedulesByStationFrom(StationDTO stationDTOFrom);

    List<ScheduleDTO> getSchedulesByStationTo(StationDTO stationDTOTo);

    List<ScheduleDTO> getSchedulesByStationsAndDate(Integer stationFromId, Integer stationToTitle, String dateTimeFrom, String dateTimeTo);

    List<TripInfo> getAllSegmentsByTripId(int tripId);

    void createTrip();

    void updateTimes(int tripId, String delayStr);

    /* void send();*/
}
