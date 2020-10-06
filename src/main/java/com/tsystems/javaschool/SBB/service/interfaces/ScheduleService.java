package com.tsystems.javaschool.SBB.service.interfaces;

import com.tsystems.javaschool.SBB.dto.ScheduleDTO;
import com.tsystems.javaschool.SBB.dto.TripInfoDTO;
import com.tsystems.javaschool.SBB.entities.Station;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleService {

    void add(ScheduleDTO scheduleDTO);

    List<ScheduleDTO> getSchedulesByStationsAndDate(Station stationFrom, Station stationTo, Timestamp tmp1, Timestamp tmp2);

    List<TripInfoDTO> getInfoOfAllTripsByTrainId(int trainId, int tripId);
}
