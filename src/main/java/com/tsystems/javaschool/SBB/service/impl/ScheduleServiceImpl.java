package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.*;
import com.tsystems.javaschool.SBB.entities.Schedule;
import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.entities.Train;
import com.tsystems.javaschool.SBB.mapper.ScheduleMapper;
import com.tsystems.javaschool.SBB.mapper.StationMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.ScheduleRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.StationRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TicketRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TrainRepository;
import com.tsystems.javaschool.SBB.service.interfaces.ScheduleService;
import com.tsystems.javaschool.SBB.service.interfaces.TicketService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    private RouteDTOContainer routeDTOContainer;


    @Override
    @Transactional
    public void add(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleMapper.toEntity(scheduleDTO);
        scheduleRepository.add(schedule);
    }

    @Override
    @Transactional
    public void createTrip() {
        Train train = trainRepository.findTrainByTrainName(routeDTOContainer.getTrainName());

        int tripId = scheduleRepository.getMaxTripId() + 1;

        Station departureStation = stationRepository.findByStationTitle(routeDTOContainer.getDepartureStationName());

        Timestamp departureTime = Timestamp.valueOf(LocalDateTime.parse(routeDTOContainer.getDepartureDate()));

        int stationIndex = 0;

        List<Station> stations = new ArrayList<>();
        stations.add(departureStation);
        stations.addAll(routeDTOContainer.getSideStations()
                .stream()
                .map(s -> stationRepository.findByStationTitle(s))
                .collect(Collectors.toList()));


        List<LocalDateTime> arrivalDates = routeDTOContainer.getSideArrivalTimes()
                .stream()
                .map(LocalDateTime::parse)
                .collect(Collectors.toList());

        List<Integer> stops = routeDTOContainer.getStops()
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        for (int i = 0; i < routeDTOContainer.getSideArrivalTimes().size(); i++) {
            Schedule schedule = new Schedule();
            schedule.setTrain(train);
            schedule.setTripId(tripId);
            schedule.setStationIndex(++stationIndex);
            schedule.setStationFrom(stations.get(i));
            schedule.setStationTo(stations.get(i + 1));
            if (i == 0) {
                schedule.setDepartureTime(departureTime);
            } else schedule.setDepartureTime(Timestamp.valueOf(arrivalDates.get(i - 1).plusMinutes(stops.get(i - 1))));
            schedule.setArrivalTime(Timestamp.valueOf(arrivalDates.get(i)));
            scheduleRepository.add(schedule);
        }

    }

    @Override
    @Transactional
    public List<ScheduleDTO> getSchedulesByStationFrom(StationDTO stationDTOFrom) {
        Station stationFrom = stationMapper.toEntity(stationDTOFrom);
        List<ScheduleDTO> schedules = scheduleMapper
                .toDTOList(scheduleRepository.getSchedulesByStationFrom(stationFrom));
        for (ScheduleDTO scheduleDTO : schedules) {
            scheduleDTO.setTripInfoDTOList(getInfoOfAllTripsByTrainIdAndTripId(scheduleDTO.getTrainDTO().getId(),
                    scheduleDTO.getTripId()));
        }
        return schedules;
    }

    @Override
    @Transactional
    public List<ScheduleDTO> getSchedulesByStationTo(StationDTO stationDTOTo) {
        Station stationTo = stationMapper.toEntity(stationDTOTo);
        List<ScheduleDTO> schedules = scheduleMapper
                .toDTOList(scheduleRepository.getSchedulesByStationTo(stationTo));
        for (ScheduleDTO scheduleDTO : schedules) {
            scheduleDTO.setTripInfoDTOList(getInfoOfAllTripsByTrainIdAndTripId(scheduleDTO.getTrainDTO().getId(),
                    scheduleDTO.getTripId()));
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

        List<ScheduleDTO> result = new ArrayList<>();

        for (ScheduleDTO schedule1 : list1) {
            List<ScheduleDTO> list2 = scheduleMapper
                    .toDTOList(scheduleRepository.getSchedulesByTrainIdTripIdStationTo(schedule1.getTrainDTO().getId(),
                            schedule1.getTripId(), stationTo));

            if(CollectionUtils.isEmpty(list2)) {
                continue;
            }

            for (ScheduleDTO schedule2 : list2) {
                if (schedule1.getId() == schedule2.getId()) {
                    schedule1.setTripInfoDTOList(getInfoOfAllTripsByTrainIdAndTripId(schedule1.getTrainDTO().getId(),
                            schedule1.getTripId()));
                    schedule1
                            .setFreePlacesCount(schedule1.getTrainDTO().getCapacity() - ticketRepository
                                    .getTakenSeatsCount(schedule1.getTrainDTO().getId(),schedule1.getTripId(),
                                            schedule1.getDepartureTime(),schedule1.getArrivalTime()).intValue());
                    schedule1.setAvailableOnTime(ticketService.isTimeValid(schedule1.getDepartureTime()));

                    result.add(schedule1);

                } else if (schedule1.getStationIndex() < schedule2.getStationIndex()
                        && !schedule1.getStationFromDTO().equals(schedule2.getStationFromDTO())) {

                    ScheduleDTO scheduleDTO = new ScheduleDTO(schedule1.getId(), schedule1.getTrainDTO(), schedule1.getTripId(),
                            schedule2.getStationIndex(),schedule1.getStationFromDTO(),schedule2.getStationToDTO(),
                            schedule1.getDepartureTime(),schedule2.getArrivalTime());

                    scheduleDTO.setTripInfoDTOList(getInfoOfAllTripsByTrainIdAndTripId(scheduleDTO.getTrainDTO().getId(),
                            scheduleDTO.getTripId()));
                    scheduleDTO.setFreePlacesCount(scheduleDTO.getTrainDTO().getCapacity() - ticketRepository
                            .getTakenSeatsCount(schedule1.getTrainDTO().getId(),schedule1.getTripId(),schedule1.getDepartureTime(),
                                    schedule1.getArrivalTime()).intValue());
                    scheduleDTO.setAvailableOnTime(ticketService.isTimeValid(scheduleDTO.getDepartureTime()));

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
                    .add(new TripInfoDTO(schedule.getTrainDTO(), schedule.getTripId(), schedule.getStationFromDTO(),
                            schedule.getStationToDTO(), schedule.getDepartureTime(), schedule.getArrivalTime()));
        }
        return tripInfoDTOList;
    }


    @Override
    @Transactional
    public List<List<TripInfoDTO>> getAllTrips(){
        List<Object[]> objectsList = scheduleRepository.getAllTrainsTrips();
        List<List<TripInfoDTO>> result = new ArrayList<>();
        for (Object[] objects : objectsList) {
           result.add(getInfoOfAllTripsByTrainIdAndTripId((int)objects[0],(int)objects[1]));
        }

        return result;
    }


    @Override
    @Transactional
    public boolean isTrainAvailableForNewTrip(String trainName, String departureTimeStr, String arrivalTimeStr) {

        int trainId = trainRepository.findTrainByTrainName(trainName).getId();

        LocalDateTime departureTime = LocalDateTime.parse(departureTimeStr);
        LocalDateTime arrivalTime = LocalDateTime.parse(arrivalTimeStr);

        List<Timestamp> departureTimes = scheduleRepository.getAllDepartureTimesByTrainId(trainId);
        if (departureTimes.isEmpty()) {
            return true;
        }
        List<Timestamp> arrivalTimes = scheduleRepository.getAllArrivalTimesByTrainId(trainId);

        Map<LocalDateTime, LocalDateTime> map = IntStream.range(0, departureTimes.size()).boxed()
                .collect(Collectors.toMap((i -> departureTimes.get(i).toLocalDateTime()), (i -> arrivalTimes.get(i).toLocalDateTime())));

        for (Map.Entry<LocalDateTime, LocalDateTime> entry : map.entrySet()) {
            if ((departureTime.isAfter(entry.getKey()) && departureTime.isBefore(entry.getValue()))
                    || (arrivalTime.isAfter(entry.getKey()) && arrivalTime.isBefore(entry.getValue()))) {
                return false;
            }
            if (departureTime.isBefore(entry.getKey()) && arrivalTime.isAfter(entry.getValue())) {
                return false;
            }
            if ((departureTime.isEqual(entry.getKey()) || departureTime.isEqual(entry.getValue()))
                    || (arrivalTime.isEqual(entry.getKey()) || arrivalTime.isEqual(entry.getValue()))) {
                return false;
            }
        }

        return true;
    }



}
