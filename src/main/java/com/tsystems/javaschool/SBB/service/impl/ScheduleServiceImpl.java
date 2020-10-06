package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.ScheduleDTO;
import com.tsystems.javaschool.SBB.dto.TripInfoDTO;
import com.tsystems.javaschool.SBB.entities.Schedule;
import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.mapper.ScheduleMapper;
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
    ScheduleRepository scheduleRepository;
    @Autowired
    ScheduleMapper scheduleMapper;

    @Override
    public void add(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleMapper.toEntity(scheduleDTO);
        scheduleRepository.add(schedule);
    }



    @Override
    @Transactional
    public List<ScheduleDTO> getSchedulesByStationsAndDate(Station stationFrom, Station stationTo, Timestamp tmp1, Timestamp tmp2) {

        List<ScheduleDTO> list1 = scheduleMapper
                .toDTOList(scheduleRepository.getSchedulesByDepartureStationAndTime(stationFrom, tmp1, tmp2));

        List<ScheduleDTO> list2 = scheduleMapper
                .toDTOList(scheduleRepository.getSchedulesByStationTo(stationTo));

        List<ScheduleDTO> result = new ArrayList<>();

        for (ScheduleDTO schedule1 : list1) {
            for (ScheduleDTO schedule2 : list2) {
                if (schedule1.getId() == schedule2.getId()) {

                    schedule1.setTripInfoDTOList(getInfoOfAllTripsByTrainId(schedule1.getTrainDTO().getId(), schedule1.getTripDTO().getId()));

                    result.add(schedule1);

                } else if (schedule1.getTrainDTO().equals(schedule2.getTrainDTO())
                        && schedule1.getTripDTO().equals(schedule2.getTripDTO())
                        && schedule1.getStationIndex() < schedule2.getStationIndex()
                        && !schedule1.getStationFromDTO().equals(schedule2.getStationFromDTO())) {

                    ScheduleDTO scheduleDTO = new ScheduleDTO(schedule1.getId(), schedule1.getTrainDTO(), schedule1.getTripDTO(),
                            schedule2.getStationIndex(),schedule1.getStationFromDTO(),schedule2.getStationToDTO(),
                            schedule1.getDepartureTime(),schedule2.getArrivalTime());

                    scheduleDTO.setTripInfoDTOList(getInfoOfAllTripsByTrainId(scheduleDTO.getTrainDTO().getId(), scheduleDTO.getTripDTO().getId()));

                    result.add(scheduleDTO);
                }
            }
        }
        return result.stream().distinct().collect(Collectors.toList());
    }


    @Override
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
