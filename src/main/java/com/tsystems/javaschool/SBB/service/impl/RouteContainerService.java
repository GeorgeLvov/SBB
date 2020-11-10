package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.RouteContainer;
import com.tsystems.javaschool.SBB.dto.RouteDTO;
import com.tsystems.javaschool.SBB.dto.StationDTO;
import com.tsystems.javaschool.SBB.repository.interfaces.StationRepository;
import com.tsystems.javaschool.SBB.service.interfaces.StationService;
import com.tsystems.javaschool.SBB.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RouteContainerService {

    @Autowired
    private RouteContainer routeContainer;
    @Autowired
    private StationService stationService;


    public void setInitialInfo(RouteDTO routeDTO) {

        StationDTO stationDTO = stationService
                .getStationDTOById(Integer.parseInt(routeDTO.getDepartureStationName()));

        routeContainer.setDepartureStation(stationDTO);
        routeContainer.setTrainName(routeDTO.getTrainName());
        routeContainer.setDepartureDate(routeDTO.getDepartureDate());
        routeContainer.setDeclaredArrivalDate(routeDTO.getDeclaredArrivalDate());
    }

    public void setSegmentsInfo(RouteDTO routeDTO) {

        routeContainer.setSideArrivalTimes(routeDTO.getSideArrivalTimes());

        routeContainer.setSideStations(routeDTO.getSideStations()
                .stream()
                .map(Integer::parseInt)
                .map(s -> stationService.getStationDTOById(s))
                .collect(Collectors.toList()));

        routeContainer.setStops(routeDTO.getStops());
    }

    public void updateSegmentsInfo(RouteDTO routeDTO) {

        routeContainer.getSideStations().addAll(routeDTO.getSideStations()
                        .stream()
                        .map(Integer::parseInt)
                        .map(s -> stationService.getStationDTOById(s))
                        .collect(Collectors.toList()));

        routeContainer.getSideArrivalTimes().addAll(routeDTO.getSideArrivalTimes());
        routeContainer.getStops().addAll(routeDTO.getStops());
    }

    public void deleteLastChange() {
        CollectionUtils.removeLast(routeContainer.getSideStations());
        CollectionUtils.removeLast(routeContainer.getSideArrivalTimes());
        CollectionUtils.removeLast(routeContainer.getStops());
    }

    public void truncate() {
        routeContainer.setTrainName(null);
        routeContainer.setDepartureStation(null);
        routeContainer.setDepartureDate(null);
        routeContainer.setDeclaredArrivalDate(null);
        routeContainer.setSideStations(null);
        routeContainer.setSideArrivalTimes(null);
        routeContainer.setStops(null);
    }


}
