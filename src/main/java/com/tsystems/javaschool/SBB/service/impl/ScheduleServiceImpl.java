package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.*;
import com.tsystems.javaschool.SBB.entities.Schedule;
import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.entities.Train;
import com.tsystems.javaschool.SBB.entities.Trip;
import com.tsystems.javaschool.SBB.mapper.ScheduleMapper;
import com.tsystems.javaschool.SBB.mapper.StationMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.*;
import com.tsystems.javaschool.SBB.service.interfaces.ScheduleService;
import com.tsystems.javaschool.SBB.service.interfaces.TicketService;
import com.tsystems.javaschool.SBB.utils.CollectionUtils;
import com.tsystems.javaschool.SBB.utils.MessageSender;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private StationMapper stationMapper;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private RouteContainer routeContainer;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private MessageSender messageSender;

    @Override
    @Transactional
    public void createTrip() {
        
        Train train = trainRepository.findTrainByName(routeContainer.getTrainName());

        Station departureStation = stationMapper.toEntity(routeContainer.getDepartureStation());

        Timestamp departureTime = Timestamp.valueOf(LocalDateTime.parse(routeContainer.getDepartureDate()));

        int stationIndex = 0;

        List<Station> stations = new ArrayList<>();

        stations.add(departureStation);

        stations.addAll(stationMapper.toEntityList(routeContainer.getSideStations()));

        List<LocalDateTime> arrivalDates = routeContainer.getSideArrivalTimes()
                .stream()
                .map(LocalDateTime::parse)
                .collect(Collectors.toList());

        List<Integer> stops = routeContainer.getStops()
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Trip trip = new Trip(train, departureStation, CollectionUtils.getLast(stations),
                departureTime, Timestamp.valueOf(CollectionUtils.getLast(arrivalDates)));

        tripRepository.add(trip);

        for (int i = 0; i < routeContainer.getSideStations().size(); i++) {
            Schedule schedule = new Schedule();
            schedule.setTrip(trip);
            schedule.setStationIndex(++stationIndex);
            schedule.setStationFrom(stations.get(i));
            schedule.setStationTo(stations.get(i + 1));
            if (i == 0) {
                schedule.setDepartureTime(departureTime);
            } else {
                schedule.setDepartureTime(Timestamp.valueOf(arrivalDates.get(i - 1).plusMinutes(stops.get(i - 1))));
            }

            schedule.setArrivalTime(Timestamp.valueOf(arrivalDates.get(i)));

            scheduleRepository.add(schedule);
        }

        messageSender.sendTextMessage("New trip was created!");

    }

    @Override
    @Transactional
    public List<ScheduleDTO> getSchedulesByStationFrom(StationDTO stationDTOFrom) {
        Station stationFrom = stationMapper.toEntity(stationDTOFrom);
        List<ScheduleDTO> resultSchedules = scheduleMapper
                .toDTOList(scheduleRepository.getSchedulesByStationFrom(stationFrom));

        for (ScheduleDTO scheduleDTO : resultSchedules) {
            scheduleDTO.setTripInfoList(getAllSegmentsByTripId(scheduleDTO.getTripDTO().getId()));
        }

        return resultSchedules;
    }

    @Override
    @Transactional
    public List<ScheduleDTO> getSchedulesByStationTo(StationDTO stationDTOTo) {
        Station stationTo = stationMapper.toEntity(stationDTOTo);
        List<ScheduleDTO> resultSchedules = scheduleMapper
                .toDTOList(scheduleRepository.getSchedulesByStationTo(stationTo));
        for (ScheduleDTO scheduleDTO : resultSchedules) {
            scheduleDTO.setTripInfoList(getAllSegmentsByTripId(scheduleDTO.getTripDTO().getId()));
        }
        return resultSchedules;
    }

    @Override
    @Transactional
    public List<ScheduleDTO> getSchedulesByStationsAndDate(Integer stationFromId, Integer stationToTitle,
                                                           String dateTimeFrom, String dateTimeTo) {

        Station stationFrom = stationRepository.getStationById(stationFromId);
        Station stationTo = stationRepository.getStationById(stationToTitle);
        Timestamp dateFrom = Timestamp.valueOf(LocalDateTime.parse(dateTimeFrom));
        Timestamp dateTo = Timestamp.valueOf(LocalDateTime.parse(dateTimeTo));

        List<ScheduleDTO> schedulesByStationFromAndDate = scheduleMapper
                .toDTOList(scheduleRepository.getSchedulesByDepartureStationAndTime(stationFrom, dateFrom, dateTo));


        List<ScheduleDTO> resultSchedules = new ArrayList<>();

        for (ScheduleDTO scheduleFrom : schedulesByStationFromAndDate) {
            List<ScheduleDTO> schedulesByStationToAndTrip = scheduleMapper
                    .toDTOList(scheduleRepository.getSchedulesByTripIdAndStationTo(scheduleFrom.getTripDTO().getId(),
                            stationTo));

            if (CollectionUtils.isEmpty(schedulesByStationToAndTrip)) {
                continue;
            }

            for (ScheduleDTO scheduleTo : schedulesByStationToAndTrip) {
                if (scheduleFrom.getId() == scheduleTo.getId()) {
                    setInfo(scheduleFrom);
                    resultSchedules.add(scheduleFrom);

                } else if (scheduleFrom.getStationIndex() < scheduleTo.getStationIndex()) {
                    scheduleTo.setStationFromDTO(scheduleFrom.getStationFromDTO());
                    scheduleTo.setDepartureTime(scheduleFrom.getDepartureTime());
                    setInfo(scheduleTo);
                    resultSchedules.add(scheduleTo);
                }
            }
        }

        return resultSchedules;
    }

    private void setInfo(ScheduleDTO scheduleDTO) {

        scheduleDTO.setTripInfoList(getAllSegmentsByTripId(scheduleDTO.getTripDTO().getId()));

        int freePlaces = scheduleDTO.getTripDTO().getTrainDTO().getCapacity() -
                ticketRepository.getTakenSeatsCount(scheduleDTO.getTripDTO().getTrainDTO().getId(),
                        scheduleDTO.getTripDTO().getId(), scheduleDTO.getDepartureTime(),
                        scheduleDTO.getArrivalTime()).intValue();

        scheduleDTO.setFreePlacesCount(Math.max(freePlaces, 0));

        scheduleDTO.setAvailableOnTime(ticketService.isTimeValid(scheduleDTO.getDepartureTime()));
    }


    @Override
    @Transactional
    public List<TripInfo> getAllSegmentsByTripId(int tripId) {

        List<ScheduleDTO> schedules = scheduleMapper
                .toDTOList(scheduleRepository.getSchedulesByTripId(tripId));
        List<TripInfo> tripInfoList = new ArrayList<>();
        for (ScheduleDTO schedule : schedules) {
            tripInfoList.add(new TripInfo(schedule.getTripDTO().getTrainDTO(), schedule.getTripDTO().getId(),
                            schedule.getStationFromDTO(), schedule.getStationToDTO(), schedule.getDepartureTime(),
                            schedule.getArrivalTime()));
        }
        return tripInfoList;
    }


    @Override
    @Transactional
    public void updateTimes(int tripId, String delayStr){
        scheduleRepository.updateTimes(tripId, delayStr);
    }
}
