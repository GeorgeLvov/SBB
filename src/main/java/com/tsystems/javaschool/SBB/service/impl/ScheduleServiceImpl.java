package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.RouteDTO;
import com.tsystems.javaschool.SBB.dto.ScheduleDTO;
import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.TripInfoDTO;
import com.tsystems.javaschool.SBB.entities.Schedule;
import com.tsystems.javaschool.SBB.entities.Station;
import com.tsystems.javaschool.SBB.entities.Train;
import com.tsystems.javaschool.SBB.mapper.ScheduleMapper;
import com.tsystems.javaschool.SBB.mapper.StationMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.ScheduleRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.StationRepository;
import com.tsystems.javaschool.SBB.repository.interfaces.TrainRepository;
import com.tsystems.javaschool.SBB.service.interfaces.ScheduleService;
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
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private StationMapper stationMapper;
    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private StationRepository stationRepository;


    @Override
    @Transactional
    public void add(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleMapper.toEntity(scheduleDTO);
        scheduleRepository.add(schedule);
    }

    @Override
    @Transactional
    public void createTrip(RouteDTO routeDTO) {
        Train train = trainRepository.findTrainByTrainName(routeDTO.getTrainName());

        int tripId = scheduleRepository.getMaxTripId() + 1;

        int stationIndex = 0;

        Station departureStation = stationRepository.findByStationTitle(routeDTO.getDepartureStationName());
        Timestamp departureTime = Timestamp.valueOf(LocalDateTime.parse(routeDTO.getDepartureDate()));

        List<Station> stations = new ArrayList<>();
        stations.add(departureStation);
        stations.addAll(routeDTO.getSideStations()
                .stream()
                .map(s -> stationRepository.findByStationTitle(s))
                .collect(Collectors.toList()));


        List<LocalDateTime> arrivalDates = routeDTO.getSideArrivalTimes()
                .stream()
                .map(LocalDateTime::parse)
                .collect(Collectors.toList());

        List<Integer> stops = routeDTO.getStops()
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        for (int i = 0; i < routeDTO.getSideArrivalTimes().size(); i++) {
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
            scheduleDTO.setTripInfoDTOList(getInfoOfAllTripsByTrainIdAndTripId(scheduleDTO.getTrainDTO().getId(), scheduleDTO.getTripId()));
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

                    schedule1.setTripInfoDTOList(getInfoOfAllTripsByTrainIdAndTripId(schedule1.getTrainDTO().getId(), schedule1.getTripId()));

                    result.add(schedule1);

                } else if (schedule1.getTrainDTO().equals(schedule2.getTrainDTO())
                        && schedule1.getTripId().equals(schedule2.getTripId())
                        && schedule1.getStationIndex() < schedule2.getStationIndex()
                        && !schedule1.getStationFromDTO().equals(schedule2.getStationFromDTO())) {

                    ScheduleDTO scheduleDTO = new ScheduleDTO(schedule1.getId(), schedule1.getTrainDTO(), schedule1.getTripId(),
                            schedule2.getStationIndex(),schedule1.getStationFromDTO(),schedule2.getStationToDTO(),
                            schedule1.getDepartureTime(),schedule2.getArrivalTime());

                    scheduleDTO.setTripInfoDTOList(getInfoOfAllTripsByTrainIdAndTripId(scheduleDTO.getTrainDTO().getId(), scheduleDTO.getTripId()));

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
            if ((departureTime.isAfter(entry.getKey()) && departureTime.isBefore(entry.getValue())) || (arrivalTime.isAfter(entry.getKey()) && arrivalTime.isBefore(entry.getValue()))) {
                return false;
            }
            if (departureTime.isBefore(entry.getKey()) && arrivalTime.isAfter(entry.getValue())) {
                return false;
            }
            if ((departureTime.isEqual(entry.getKey()) || departureTime.isEqual(entry.getValue())) || (arrivalTime.isEqual(entry.getKey()) || arrivalTime.isEqual(entry.getValue()))) {
                return false;
            }
        }

        return true;
    }

}
