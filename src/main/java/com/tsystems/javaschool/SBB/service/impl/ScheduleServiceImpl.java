package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.ScheduleDTO;
import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.TripInfoDTO;
import com.tsystems.javaschool.SBB.entities.Schedule;
import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.mapper.ScheduleMapper;
import com.tsystems.javaschool.SBB.mapper.StationMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.ScheduleRepository;
import com.tsystems.javaschool.SBB.service.interfaces.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private StationMapper stationMapper;

    @Override
    @Transactional
    public void add(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleMapper.toEntity(scheduleDTO);
        scheduleRepository.add(schedule);
    }


    @Override
    @Transactional
    public List<ScheduleDTO> getSchedulesByStationFrom(StationDTO stationDTOFrom){
        Station stationFrom = stationMapper.toEntity(stationDTOFrom);
        List<ScheduleDTO> schedules = scheduleMapper
                .toDTOList(scheduleRepository.getSchedulesByStationFrom(stationFrom));
        for (ScheduleDTO scheduleDTO : schedules){
           scheduleDTO.setTripInfoDTOList(getInfoOfAllTripsByTrainIdAndTripId(scheduleDTO.getTrainDTO().getId(), scheduleDTO.getTripDTO().getId()));
        }
        return schedules;
    }

    @Override
    @Transactional
    public List<ScheduleDTO> getSchedulesByStationsAndDate(StationDTO stationDTOFrom, StationDTO stationDTOTo, Timestamp tmp1, Timestamp tmp2) {

        Station stationFrom = stationMapper.toEntity(stationDTOFrom);
        Station stationTo = stationMapper.toEntity(stationDTOTo);

        List<ScheduleDTO> list1 = scheduleMapper
                .toDTOList(scheduleRepository.getSchedulesByDepartureStationAndTime(stationFrom, tmp1, tmp2));

        List<ScheduleDTO> list2 = scheduleMapper
                .toDTOList(scheduleRepository.getSchedulesByStationTo(stationTo));

        List<ScheduleDTO> result = new ArrayList<>();

        for (ScheduleDTO schedule1 : list1) {
            for (ScheduleDTO schedule2 : list2) {
                if (schedule1.getId() == schedule2.getId()) {

                    schedule1.setTripInfoDTOList(getInfoOfAllTripsByTrainIdAndTripId(schedule1.getTrainDTO().getId(), schedule1.getTripDTO().getId()));

                    result.add(schedule1);

                } else if (schedule1.getTrainDTO().equals(schedule2.getTrainDTO())
                        && schedule1.getTripDTO().equals(schedule2.getTripDTO())
                        && schedule1.getStationIndex() < schedule2.getStationIndex()
                        && !schedule1.getStationFromDTO().equals(schedule2.getStationFromDTO())) {

                    ScheduleDTO scheduleDTO = new ScheduleDTO(schedule1.getId(), schedule1.getTrainDTO(), schedule1.getTripDTO(),
                            schedule2.getStationIndex(),schedule1.getStationFromDTO(),schedule2.getStationToDTO(),
                            schedule1.getDepartureTime(),schedule2.getArrivalTime());

                    scheduleDTO.setTripInfoDTOList(getInfoOfAllTripsByTrainIdAndTripId(scheduleDTO.getTrainDTO().getId(), scheduleDTO.getTripDTO().getId()));

                    result.add(scheduleDTO);
                }
            }
        }
        return result.stream().distinct().collect(Collectors.toList());
    }


    @Override
    @Transactional
    public List<TripInfoDTO> getInfoOfAllTripsByTrainIdAndTripId(int trainId, int tripId) {
        List<ScheduleDTO> schedules = scheduleMapper.toDTOList(scheduleRepository.getSchedulesByTrainIdAndTripId(trainId, tripId));
        List<TripInfoDTO> tripInfoDTOList = new ArrayList<>();
        for (ScheduleDTO schedule : schedules) {
            tripInfoDTOList
                    .add(new TripInfoDTO(schedule.getStationFromDTO(), schedule.getStationToDTO(), schedule.getDepartureTime(), schedule.getArrivalTime()));
        }
        return tripInfoDTOList;
    }

}
