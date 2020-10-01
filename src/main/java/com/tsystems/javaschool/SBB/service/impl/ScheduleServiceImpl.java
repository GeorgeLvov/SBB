package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.ScheduleDTO;
import com.tsystems.javaschool.SBB.dto.TripInfoDTO;
import com.tsystems.javaschool.SBB.entities.Schedule;
import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.mapper.ScheduleMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl {

    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    ScheduleMapper scheduleMapper;

    @Transactional
    public List<ScheduleDTO> getSchedulesByStationsAndDate(Station stationFrom, Station stationTo, Timestamp tmp1, Timestamp tmp2) {

        List<ScheduleDTO> list1 = scheduleMapper
                .toDTOList(scheduleRepository.getSchedulesByDepartureStationAndTime(stationFrom, tmp1, tmp2));

        List<ScheduleDTO> list2 = scheduleMapper.toDTOList(scheduleRepository.getSchedulesByStationTo(stationTo));

        List<ScheduleDTO> result = new ArrayList<>();

        for (ScheduleDTO schedule1 : list1) {
            for (ScheduleDTO schedule2 : list2) {
                if (schedule1.getId() == schedule2.getId()) {
                    schedule1.setTripInfoDTOList(getInfoOfAllTripsByTrainId(schedule1.getTrainDTO().getId(), schedule1.getTripDTO().getId()));
                    result.add(schedule1);
                } else if (schedule1.getTrainDTO().equals(schedule2.getTrainDTO())
                        && schedule1.getTripDTO().equals(schedule2.getTripDTO())
                        && schedule1.getStationIndex() < schedule2.getStationIndex()) {
                    ScheduleDTO scheduleDTO = new ScheduleDTO(schedule1.getId(), schedule1.getTrainDTO(), schedule1.getTripDTO(),
                            schedule2.getStationIndex(),schedule1.getStationFromDTO(),schedule2.getStationToDTO(),
                            schedule1.getDepartureTime(),schedule2.getArrivalTime());
                    scheduleDTO.setTripInfoDTOList(getInfoOfAllTripsByTrainId(scheduleDTO.getTrainDTO().getId(), scheduleDTO.getTripDTO().getId()));
                    result.add(scheduleDTO);
                }

            }
        }

        return result;
    }

    @Transactional
    public List<TripInfoDTO> getInfoOfAllTripsByTrainId(int trainId, int tripId){
        List<Schedule> schedules = scheduleRepository.getSchedulesByTrainIdAndTripId(trainId,tripId);
        List<TripInfoDTO> tripInfoDTOList = new ArrayList<>();
        for (Schedule schedule : schedules) {
            tripInfoDTOList
                    .add(new TripInfoDTO(schedule.getStationFrom(),schedule.getStationTo(),schedule.getDepartureTime(),schedule.getArrivalTime()));
        }
        return tripInfoDTOList;
    }
}

/*
 @Transactional
    public List<Schedule> getSchedulesByStationsAndDate(Station stationFrom, Station stationTo, Timestamp tmp1, Timestamp tmp2) {

        List<Schedule> list1 = scheduleRepository.getSchedulesByDepartureStationAndTime(stationFrom, tmp1, tmp2);
        List<Schedule> list2 = scheduleRepository.getSchedulesByStationTo(stationTo);

        List<Schedule> result = new ArrayList<>();

        for (Schedule schedule1 : list1) {
            for (Schedule schedule2 : list2) {
                if (schedule1.getId() == schedule2.getId()) {
                    result.add(schedule1);
                } else if (schedule1.getTrain().equals(schedule2.getTrain())
                        && schedule1.getTrip().equals(schedule2.getTrip())
                        && schedule1.getStationIndex() < schedule2.getStationIndex()) {
                    result.add(new Schedule(schedule1.getId(), schedule1.getTrain(), schedule1.getTrip(),
                    schedule2.getStationIndex(),schedule1.getStationFrom(),schedule2.getStationTo(),
                            schedule1.getDepartureTime(),schedule2.getArrivalTime()));
                }
            }
        }

        return result;
    }

    @Transactional
    public List<TripInfoDTO> getInfoOfAllTripsByTrainId(int trainId, int tripId){
        List<Schedule> schedules = scheduleRepository.getSchedulesByTrainIdAndTripId(trainId,tripId);
        List<TripInfoDTO> tripInfoDTOList = new ArrayList<>();
        for (Schedule schedule : schedules) {
            tripInfoDTOList
                    .add(new TripInfoDTO(schedule.getStationFrom(),schedule.getStationTo(),schedule.getDepartureTime(),schedule.getArrivalTime()));
        }
        return tripInfoDTOList;
    }*/
