package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.RouteContainer;
import com.tsystems.javaschool.SBB.dto.RouteDTO;
import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.dto.TrainDTO;
import com.tsystems.javaschool.SBB.service.interfaces.RouteContainerService;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import com.tsystems.javaschool.SBB.service.interfaces.TrainService;
import com.tsystems.javaschool.SBB.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RouteContainerServiceImpl implements RouteContainerService {

    @Autowired
    private RouteContainer routeContainer;
    @Autowired
    private StationService stationService;
    @Autowired
    private TrainService trainService;

    @Override
    public void setInitialInfo(RouteDTO routeDTO) {
        TrainDTO trainDTO = trainService.getTrainDTOById(routeDTO.getTrainId());
        StationDTO stationDTO = stationService.getStationDTOById(routeDTO.getDepartureStationId());
        routeContainer.setTrainDTO(trainDTO);
        routeContainer.setDepartureStation(stationDTO);
        routeContainer.setDepartureDate(routeDTO.getDepartureDate());
        routeContainer.setDeclaredArrivalDate(routeDTO.getDeclaredArrivalDate());
    }

    @Override
    public void setSegmentsInfo(RouteDTO routeDTO) {
        routeContainer.setSideArrivalTimes(routeDTO.getSideArrivalTimes());
        routeContainer.setSideStations(routeDTO.getSideStations()
                .stream()
                .map(Integer::parseInt)
                .map(s -> stationService.getStationDTOById(s))
                .collect(Collectors.toList()));
        routeContainer.setStops(routeDTO.getStops());
    }

    @Override
    public void updateSegmentsInfo(RouteDTO routeDTO) {

        StationDTO stationDTO = stationService
                .getStationDTOById(Integer.parseInt(CollectionUtils.getLast(routeDTO.getSideStations())));

        routeContainer.getSideStations().add(stationDTO);
        routeContainer.getSideArrivalTimes().add(CollectionUtils.getLast(routeDTO.getSideArrivalTimes()));
        routeContainer.getStops().add(CollectionUtils.getLast(routeDTO.getStops()));
    }

    @Override
    public void deleteLastChange() {
        CollectionUtils.removeLast(routeContainer.getSideStations());
        CollectionUtils.removeLast(routeContainer.getSideArrivalTimes());
        CollectionUtils.removeLast(routeContainer.getStops());
    }

    @Override
    public void truncateContainer() {
        routeContainer.setTrainDTO(null);
        routeContainer.setDepartureStation(null);
        routeContainer.setDepartureDate(null);
        routeContainer.setDeclaredArrivalDate(null);
        routeContainer.setSideStations(null);
        routeContainer.setSideArrivalTimes(null);
        routeContainer.setStops(null);
    }


}
